// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Statement;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Algol68_IfStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Algol68_Keyword IF = new Algol68_Keyword("IF");
	public @S(20) Algol68_Expression condition;
	public @S(30) Algol68_Keyword THEN = new Algol68_Keyword("THEN");
	public @S(40) TokenList<Algol68_Statement> thenStatements;
	public @S(50) @OPT TokenList<Algol68_IfElifClause> elifClauses;
	public @S(60) @OPT Algol68_IfElseClause elseClause;
	public @S(70) Algol68_Keyword END = new Algol68_Keyword("FI");
	public @S(80) @OPT PunctuationSemicolon semicolon;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Algol68_IfElifClause extends TokenSequence
	{
		public @S(10) Algol68_Keyword ELIF = new Algol68_Keyword("ELIF");
		public @S(20) Algol68_Expression condition;
		public @S(30) Algol68_Keyword THEN = new Algol68_Keyword("THEN");
		public @S(40) TokenList<Algol68_Statement> elifStatements;
	}

	public static class Algol68_IfElseClause extends TokenSequence
	{
		public @S(10) Algol68_Keyword ELSE = new Algol68_Keyword("ELSE");
		public @S(20) TokenList<Algol68_Statement> elseStatements;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<Algol68_Statement> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));

			if (elifClauses != null)
			{
				for (Algol68_IfElifClause elif : elifClauses._elements)
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elif.ELIF));
				}
			}

			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
			}
		}

		boolean cond1 = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond1);
		if (cond1)
		{
			todo = thenStatements;
		}
		else
		{
			int seq = 1;
			
			// Check for each 'else if'
			if (elifClauses != null && elifClauses.size() > 0)
			{
				for (Algol68_IfElifClause elif : elifClauses._elements)
				{
					boolean cond2 = interpreter.getBoolValue(elif.condition);
					_metrics.get(seq).completedIf(cond2);
					seq++;
					if (cond2)
					{
						todo = elif.elifStatements;
						break;
					}
				}
			}

			// Check for 'else'
			if (todo == null)
			{
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.get(seq).completedIf(true);
					todo = elseClause.elseStatements;
				}
			}
		}

		if (todo != null)
		{
			result = Eagle_Statement_Result.NORMAL;
			for (Algol68_Statement stmt : todo._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> ifTrue = new ArrayList<AbstractStatement>();
		ArrayList<AbstractStatement> ifFalse = new ArrayList<AbstractStatement>();

		for (Algol68_Statement thenStatement : thenStatements._elements)
		{
			ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator, thenStatement.getWhich());
			if (stmts != null)
			{
				for (AbstractStatement stmt : stmts)
				{
					ifTrue.add(stmt);
				}
			}
		}

		if (elseClause != null && elseClause.isPresent())
		{
			for (Algol68_Statement elseStatement : elseClause.elseStatements._elements)
			{
				for (AbstractStatement stmt : transformer.transformStatement(generator, elseStatement.getWhich()))
				{
					ifFalse.add(stmt);
				}
			}
		}

		if (elifClauses == null || elifClauses.size() == 0)
		{
			return generator.newIfStatement(cond, ifTrue, ifFalse, this);
		}

		// Dang, need some "else if" blocks
		ArrayList<AbstractExpression> elseIfConds =
				new ArrayList<AbstractExpression>();
		ArrayList<ArrayList<AbstractStatement>> elseIfParts =
				new ArrayList<ArrayList<AbstractStatement>>();
		for (Algol68_IfElifClause nextElIf : elifClauses._elements)
		{
			elseIfConds.add(transformer.transformExpression(generator,
					nextElIf.condition));
			for (Algol68_Statement stmt : nextElIf.elifStatements._elements)
			{
				elseIfParts.add(transformer.transformStatement(generator, stmt));
			}
		}
		return generator.newIfElseIfStatement(cond, ifTrue,
				elseIfConds, elseIfParts, ifFalse, this);
	}
}
