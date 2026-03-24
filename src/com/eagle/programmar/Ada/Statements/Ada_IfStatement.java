// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Ada_Statement;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
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

public class Ada_IfStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Ada_Keyword IF = new Ada_Keyword("if");
	public @S(20) Ada_Expression condition;
	public @S(30) Ada_Keyword THEN = new Ada_Keyword("then");
	public @S(40) TokenList<Ada_Statement> thenStatements;
	public @S(50) @OPT TokenList<Ada_ElseIfClause> elseIfClauses;
	public @S(60) @OPT Ada_IfElseClause elseClause;
	public @S(70) Ada_Keyword END = new Ada_Keyword("end");
	public @S(80) Ada_Keyword IF2 = new Ada_Keyword("if");
	public @S(90) PunctuationSemicolon semicolon;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Ada_IfElseClause extends TokenSequence
	{
		public @S(10) Ada_Keyword ELSE = new Ada_Keyword("else");
		public @S(20) TokenList<Ada_Statement> elseStatements;
	}

	public static class Ada_ElseIfClause extends TokenSequence
	{
		public @S(10) Ada_Keyword ELSIF = new Ada_Keyword("elsif");
		public @S(20) Ada_Expression condition;
		public @S(30) Ada_Keyword THEN = new Ada_Keyword("then");
		public @S(40) TokenList<Ada_Statement> elseStatements;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<Ada_Statement> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
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
			// Check for 'else'
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.get(1).completedIf(true);
				todo = elseClause.elseStatements;
			}
		}

		if (todo != null)
		{
			result = Eagle_Statement_Result.NORMAL;
			for (Ada_Statement stmt : todo._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
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

		for (Ada_Statement thenStatement : thenStatements._elements)
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
			for (Ada_Statement elseStatement : elseClause.elseStatements._elements)
			{
				for (AbstractStatement stmt : transformer.transformStatement(generator, elseStatement.getWhich()))
				{
					ifFalse.add(stmt);
				}
			}
		}

		AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
		return stmt;
	}
}
