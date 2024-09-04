// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Statement;
import com.eagle.programmar.VB.VB_Statement.VB_BaseStatement;
import com.eagle.programmar.VB.Terminals.VB_Comment;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class VB_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("752y8abs.aspx") VB_Keyword IF1 = new VB_Keyword("if");
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
		public @S(10) VB_BaseStatement thenStatement;
	}

	public static class VB_IfMultiLiner extends TokenSequence
	{
		public @S(10) VB_EndOfLine eoln;
		public @S(20) TokenList<VB_Statement> thenStatement;
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
		public @S(60) TokenList<VB_Statement> elseIfStatement;
	}

	public static class VB_IfElseClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<VB_Comment> comments;
		public @S(20) VB_Keyword ELSE = new VB_Keyword("else");
		public @S(30) VB_EndOfLine eoln;
		public @S(40) TokenList<VB_Statement> elseStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<VB_Statement> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, this));
			
			if (ifType.getWhich() instanceof VB_IfMultiLiner)
			{
				VB_IfMultiLiner multi = (VB_IfMultiLiner) ifType.getWhich();
				if (multi.elseIfClause != null)
				{
					for (VB_IfElseIfClause elif : multi.elseIfClause._elements)
					{
						_metrics.add(new IfCondMetrics(interpreter._metrics, elif));
					}
				}
	
				if (multi.elseClause != null && multi.elseClause.isPresent())
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, multi.elseClause));
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
				if (multi.elseIfClause != null)
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
				for (VB_Statement stmt : todo._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}
			}
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> ifTrue = new ArrayList<AbstractStatement>();
		ArrayList<AbstractStatement> ifFalse = new ArrayList<AbstractStatement>();
		
		AbstractToken which = ifType.getWhich();
		if (which instanceof VB_IfOneLiner)
		{
			VB_IfOneLiner oneLiner = (VB_IfOneLiner) which;
			VB_Statement statement = new VB_Statement();
			statement.baseStatement = oneLiner.thenStatement;
			ifTrue.add(transformer.transformStatement(generator, statement.baseStatement.getWhich()));
		}
		else
		{
			VB_IfMultiLiner multiLiner = (VB_IfMultiLiner) which;
			for (VB_Statement statement : multiLiner.thenStatement._elements)
			{
				ifTrue.add(transformer.transformStatement(generator, statement.baseStatement.getWhich()));
			}
			
			if (multiLiner.elseIfClause != null && multiLiner.elseIfClause.isPresent() && multiLiner.elseIfClause.size() > 0)
			{
				throw new RuntimeException("Can't handle VB elseif yet. Has to recurse here.");
			}
			
			if (multiLiner.elseClause != null && multiLiner.elseClause.isPresent())
			{
				for (VB_Statement statement : multiLiner.elseClause.elseStatement._elements)
				{
					ifFalse.add(transformer.transformStatement(generator, statement.baseStatement.getWhich()));
				}
			}
		}
		
		AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
		return stmt;
	}
}
