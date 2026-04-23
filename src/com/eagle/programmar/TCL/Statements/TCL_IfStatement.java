// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.TCL.TCL_Element.TCL_Statement;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.programmar.TCL.Terminals.TCL_PunctuationChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class TCL_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("TclCmd/if.html") TCL_Keyword IF = new TCL_Keyword("if");
	public @S(20) TCL_PunctuationChoice left = new TCL_PunctuationChoice("{", "(");
	public @S(30) TCL_Expression condition;
	public @S(40) TCL_PunctuationChoice right = new TCL_PunctuationChoice("}", ")");
	public @S(50) TCL_Statement thenStatement;
	public @S(60) @OPT TokenList<TCL_ElseIfClause> elseIfClauses;
	public @S(70) @OPT TCL_ElseClause elseClause;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class TCL_ElseClause extends TokenSequence
	{
		public @S(10) TCL_Keyword ELSE = new TCL_Keyword("else");
		public @S(20) TCL_Statement elseStatement;
	}

	public static class TCL_ElseIfClause extends TokenSequence
	{
		public @S(10) TCL_Keyword ELSEIF = new TCL_Keyword("elseif");
		public @S(20) TCL_PunctuationChoice left = new TCL_PunctuationChoice("{", "(");
		public @S(30) TCL_Expression condition;
		public @S(40) TCL_PunctuationChoice right = new TCL_PunctuationChoice("}", ")");
		public @S(50) TCL_Statement elseIfStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TCL_Statement todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
			
			if (elseIfClauses != null && elseIfClauses.size() > 0)
			{
				for (TCL_ElseIfClause elseIfClause : elseIfClauses._elements)
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elseIfClause.ELSEIF));
				}
			}

			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
			}
		}

		boolean cond = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond);
		if (cond)
		{
			todo = thenStatement;
		}
		else
		{
			boolean matched = false;
			int seq = 1;
			if (elseIfClauses != null && elseIfClauses.size() > 0)
			{
				for (TCL_ElseIfClause elseIfClause : elseIfClauses._elements)
				{
					boolean cond2 = interpreter.getBoolValue(elseIfClause.condition);
					_metrics.get(seq).completedIf(cond2);
					if (cond2)
					{
						todo = elseIfClause.elseIfStatement;
						matched = true;
						break;
					}
					seq++;
				}
			}
			
			if (!matched)
			{
				// Check for 'else'
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.get(seq).completedIf(true);
					todo = elseClause.elseStatement;
				}
			}
		}

		if (todo != null)
		{
			result = interpreter.tryToInterpret(todo);
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

		ArrayList<AbstractStatement> stmts1 = transformer.transformStatement(generator, thenStatement.getWhich());
		if (stmts1 != null)
		{
			for (AbstractStatement stmt : stmts1)
			{
				ifTrue.add(stmt);
			}
		}

		if (elseClause != null && elseClause.isPresent())
		{
			ArrayList<AbstractStatement> stmts2 = transformer.transformStatement(generator,
					elseClause.elseStatement.getWhich());
			for (AbstractStatement stmt : stmts2)
			{
				ifFalse.add(stmt);
			}
		}

		if (elseIfClauses == null || elseIfClauses.size() == 0)
		{
			return generator.newIfStatement(cond, ifTrue, ifFalse, this);
		}

		// Dang, need some "else if" blocks
		ArrayList<AbstractExpression> elseIfConds =
				new ArrayList<AbstractExpression>();
		ArrayList<ArrayList<AbstractStatement>> elseIfParts =
				new ArrayList<ArrayList<AbstractStatement>>();
		for (TCL_ElseIfClause nextElIf : elseIfClauses._elements)
		{
			elseIfConds.add(transformer.transformExpression(generator,
					nextElIf.condition));
			ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator,
					nextElIf.elseIfStatement);
			elseIfParts.add(stmts);
		}
		return generator.newIfElseIfStatement(cond, ifTrue,
				elseIfConds, elseIfParts, ifFalse, this);
	}
}
