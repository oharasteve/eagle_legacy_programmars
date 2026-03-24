// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 20, 2025

package com.eagle.programmar.SQL.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Expressions.SQL_VariableExpression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class SQL_CaseStatement extends TokenSequence
		implements EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) SQL_Keyword SET = new SQL_Keyword("SET");
	public @S(20) SQL_VariableExpression var;
	public @S(30) PunctuationEquals equals;
	public @S(40) SQL_Keyword CASE = new SQL_Keyword("CASE");
	public @S(50) SQL_Expression expression;
	public @S(60) TokenList<SQL_CaseWhenClause> whenThens;
	public @S(70) @OPT SQL_CaseElseClause elseClause;
	public @S(80) SQL_Keyword END = new SQL_Keyword("END");
	public @S(90) PunctuationSemicolon semicolon;

	public static class SQL_CaseWhenClause extends TokenSequence
	{
		public @S(10) SQL_Keyword WHEN = new SQL_Keyword("WHEN");
		public @S(20) SQL_Expression whenExpression;
		public @S(30) SQL_Keyword THEN = new SQL_Keyword("THEN");
		public @S(40) SQL_Expression thenExpression;
	}

	public static class SQL_CaseElseClause extends TokenSequence
	{
		public @S(10) SQL_Keyword ELSE = new SQL_Keyword("ELSE");
		public @S(20) SQL_Expression elseExpression;
	}

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, CASE));

			for (int i = 0; i < whenThens.size(); i++)
			{
				SQL_CaseWhenClause when = whenThens._elements.get(i);
				_metrics.add(new IfCondMetrics(interpreter._metrics, when.WHEN));
			}

			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
			}
		}

		int value = interpreter.getIntValue(expression);
		EagleValue newValue = null;
		for (int i = 0; i < whenThens.size(); i++)
		{
			SQL_CaseWhenClause when = whenThens._elements.get(i);
			int whenValue = interpreter.getIntValue(when.whenExpression);
			boolean matches = value == whenValue;
			_metrics.get(i).completedIf(matches);

			if (matches)
			{
				newValue = interpreter.getEagleValue(when.thenExpression);
				break;
			}
		}

		if (newValue == null && elseClause != null && elseClause.isPresent())
		{
			_metrics.get(whenThens.size()).completedIf(true);
			newValue = interpreter.getEagleValue(elseClause.elseExpression);
		}

		SQL_Identifier_Reference id = var.variable.ids.first();
		interpreter.setSymbol(var, id.getValue(), newValue);
		return Eagle_Statement_Result.NORMAL;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression newExpr = transformer.transformExpression(generator, expression);
		SQL_Identifier_Reference id = var.variable.ids.first();

		ArrayList<AbstractExpression> values = new ArrayList<AbstractExpression>();
		ArrayList<ArrayList<AbstractStatement>> cases = new ArrayList<ArrayList<AbstractStatement>>();
		for (int i = 0; i < whenThens.size(); i++)
		{
			SQL_CaseWhenClause when = whenThens._elements.get(i);
			ArrayList<AbstractStatement> thisCase = new ArrayList<AbstractStatement>();
			values.add(transformer.transformExpression(generator, when.whenExpression));

			AbstractExpression thisValue = transformer.transformExpression(generator, when.thenExpression);
			AbstractExpression thisAsgExpr = generator.newAssignmentExpression(id.getValue(),
					SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, thisValue, when);
			thisCase.add(generator.newExpressionStatement(thisAsgExpr, when));

			cases.add(thisCase);
		}

		ArrayList<AbstractStatement> defaultCase = null;
		if (elseClause != null && elseClause.isPresent())
		{
			defaultCase = new ArrayList<AbstractStatement>();
			AbstractExpression defaultValue = transformer.transformExpression(generator, elseClause.elseExpression);
			AbstractExpression defaultAsgExpr = generator.newAssignmentExpression(id.getValue(),
					SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, defaultValue, elseClause);
			defaultCase.add(generator.newExpressionStatement(defaultAsgExpr, elseClause));
		}

		AbstractStatement stmt = generator.newSwitchStatement(newExpr, values, cases, defaultCase, this);
		return stmt;
	}
}
