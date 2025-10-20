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
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
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
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		return null; // TODO
		
//		AbstractExpression cond = transformer.transformExpression(generator, condition);
//		ArrayList<AbstractStatement> ifTrue = new ArrayList<AbstractStatement>();
//		ArrayList<AbstractStatement> ifFalse = new ArrayList<AbstractStatement>();
//		
//		for (SQL_StatementOrComment thenStatement : statements._elements)
//		{
//			ArrayList<AbstractStatement> stmts1 = transformer.transformStatement(generator,
//					thenStatement.getWhich());
//			if (stmts1 != null)
//			{
//				for (AbstractStatement stmt1 : stmts1)
//				{
//					ifTrue.add(stmt1);
//				}
//			}
//		}
//		
//		if (elseClause != null && elseClause.isPresent())
//		{
//			for (SQL_StatementOrComment elseStatement : elseClause.statements._elements)
//			{
//				ArrayList<AbstractStatement> stmts2 = transformer.transformStatement(generator,
//						elseStatement.getWhich());
//				if (stmts2 != null)
//				{
//					for (AbstractStatement stmt2 : stmts2)
//					{
//						ifFalse.add(stmt2);
//					}
//				}
//			}
//		}
//		
//		AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
//		return stmt;
	}
}
