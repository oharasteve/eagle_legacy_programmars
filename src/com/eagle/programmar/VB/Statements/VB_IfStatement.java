// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.VB.VB_Element;
import com.eagle.programmar.VB.VB_Element.VB_Statement;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Comment;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class VB_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("statements/if-then-else-statement") VB_Keyword IF1 = new VB_Keyword("if");
	public @S(20) VB_Expression condition;
	public @S(30) VB_Keyword THEN = new VB_Keyword("then");
	public @S(40) VB_IfType ifType;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class VB_IfType extends TokenChooser
	{
		public @CHOICE VB_IfOneLiner XXoneLiner;
		public @CHOICE VB_IfMultiLiner XXmultiLiner;
	}

	public static class VB_IfOneLiner extends TokenSequence
	{
		public @S(10) VB_Statement thenStatement;
	}

	public static class VB_IfMultiLiner extends TokenSequence
	{
		public @S(10) VB_EndOfLine eoln;
		public @S(20) TokenList<VB_Element> thenStatement;
		public @S(30) @OPT TokenList<VB_IfElseIfClause> elseIfClause;
		public @S(40) @OPT VB_IfElseClause elseClause;
		public @S(50) VB_Keyword END = new VB_Keyword("end");
		public @S(60) VB_Keyword IF2 = new VB_Keyword("if");
	}

	public static class VB_IfElseIfClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<VB_Comment> comments;
		public @S(20) VB_Keyword ELSEIF = new VB_Keyword("elseif");
		public @S(30) VB_Expression condition;
		public @S(40) VB_Keyword THEN = new VB_Keyword("then");
		public @S(50) VB_EndOfLine eoln;
		public @S(60) TokenList<VB_Element> elseIfStatement;
	}

	public static class VB_IfElseClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<VB_Comment> comments;
		public @S(20) VB_Keyword ELSE = new VB_Keyword("else");
		public @S(30) VB_EndOfLine eoln;
		public @S(40) TokenList<VB_Element> elseStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<VB_Element> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF1));

			if (ifType.getWhich() instanceof VB_IfMultiLiner)
			{
				VB_IfMultiLiner multi = (VB_IfMultiLiner) ifType.getWhich();
				if (multi.elseIfClause != null && multi.elseIfClause.isPresent())
				{
					for (VB_IfElseIfClause elif : multi.elseIfClause._elements)
					{
						_metrics.add(new IfCondMetrics(interpreter._metrics, elif.ELSEIF));
					}
				}

				if (multi.elseClause != null && multi.elseClause.isPresent())
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, multi.elseClause.ELSE));
				}
			}
		}

		boolean cond1 = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond1);

		if (ifType.getWhich() instanceof VB_IfOneLiner)
		{
			VB_IfOneLiner oneLiner = (VB_IfOneLiner) ifType.getWhich();
			if (cond1)
			{
				result = interpreter.tryToInterpret(oneLiner.thenStatement);
			}
		}
		else
		{
			VB_IfMultiLiner multi = (VB_IfMultiLiner) ifType.getWhich();
			if (cond1)
			{
				todo = multi.thenStatement;
			}
			else
			{
				int seq = 1;
				// Check for each 'else if'
				if (multi.elseIfClause != null && multi.elseIfClause.isPresent())
				{
					for (VB_IfElseIfClause elif : multi.elseIfClause._elements)
					{
						boolean cond2 = interpreter.getBoolValue(elif.condition);
						_metrics.get(seq).completedIf(cond2);
						seq++;
						if (cond2)
						{
							todo = elif.elseIfStatement;
							break;
						}
					}
				}

				// Check for 'else'
				if (todo == null)
				{
					if (multi.elseClause != null && multi.elseClause.isPresent())
					{
						_metrics.get(seq).completedIf(true);
						todo = multi.elseClause.elseStatement;
					}
				}
			}

			if (todo != null)
			{
				result = Eagle_Statement_Result.NORMAL;
				for (VB_Element stmt : todo._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}
			}
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> ifTrue = new ArrayList<AbstractStatement>();
		ArrayList<AbstractStatement> ifFalse = new ArrayList<AbstractStatement>();

		AbstractToken which = ifType.getWhich();
		if (which instanceof VB_IfOneLiner)
		{
			VB_IfOneLiner oneLiner = (VB_IfOneLiner) which;
			VB_Element statement = new VB_Element();
			statement.baseStatements = new SeparatedList<VB_Statement, PunctuationColon>();
			statement.baseStatements.addPrimaryElement(oneLiner.thenStatement);
			for (AbstractStatement stmt : transformer.transformStatement(generator, statement.baseStatements.first().getWhich()))
			{
				ifTrue.add(stmt);
			}
			return generator.newIfStatement(cond, ifTrue, ifFalse, this);
		}

		VB_IfMultiLiner multiLiner = (VB_IfMultiLiner) which;
		for (VB_Element statement : multiLiner.thenStatement._elements)
		{
			for (int i = 0; i < statement.baseStatements.getPrimaryCount(); i++)
			{
				VB_Statement baseStatement = statement.baseStatements.getPrimaryElement(i);
				ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator,
						baseStatement.getWhich());
				if (stmts != null)
				{
					for (AbstractStatement stmt : stmts)
					{
						ifTrue.add(stmt);
					}
				}
			}
		}

		if (multiLiner.elseClause != null && multiLiner.elseClause.isPresent())
		{
			for (VB_Element statement : multiLiner.elseClause.elseStatement._elements)
			{
				for (int i = 0; i < statement.baseStatements.getPrimaryCount(); i++)
				{
					VB_Statement baseStatement = statement.baseStatements.getPrimaryElement(i);
					for (AbstractStatement stmt : transformer.transformStatement(generator,
							baseStatement.getWhich()))
					{
						ifFalse.add(stmt);
					}
				}
			}
		}

		if (multiLiner.elseIfClause == null || multiLiner.elseIfClause.size() == 0)
		{
			return generator.newIfStatement(cond, ifTrue, ifFalse, this);
		}

		// Dang, need some "else if" blocks
		ArrayList<AbstractExpression> elseIfConds =
				new ArrayList<AbstractExpression>();
		ArrayList<ArrayList<AbstractStatement>> elseIfParts =
				new ArrayList<ArrayList<AbstractStatement>>();
		for (VB_IfElseIfClause nextElIf : multiLiner.elseIfClause._elements)
		{
			elseIfConds.add(transformer.transformExpression(generator,
					nextElIf.condition));
			ArrayList<AbstractStatement> elseIfPart = new ArrayList<AbstractStatement>();
			for (VB_Element statement : nextElIf.elseIfStatement._elements)
			{
				for (int i = 0; i < statement.baseStatements.getPrimaryCount(); i++)
				{
					VB_Statement baseStatement = statement.baseStatements.getPrimaryElement(i);
					for (AbstractStatement stmt : transformer.transformStatement(generator,
							baseStatement.getWhich()))
					{
						elseIfPart.add(stmt);
					}
				}
			}
			elseIfParts.add(elseIfPart);
		}
		return generator.newIfElseIfStatement(cond, ifTrue,
				elseIfConds, elseIfParts, ifFalse, this);
	}
}
