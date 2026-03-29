// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 20, 2025

namespace com.eagle.programmar.SQL.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_VariableExpression = com.eagle.programmar.SQL.Expressions.SQL_VariableExpression;
	using SQL_Identifier_Reference = com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class SQL_CaseStatement : TokenSequence, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword SET = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("SET");
		public SQL_Keyword SET = new SQL_Keyword("SET");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Expressions.SQL_VariableExpression var;
		public SQL_VariableExpression var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.SQL.Terminals.SQL_Keyword CASE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("CASE");
		public SQL_Keyword CASE = new SQL_Keyword("CASE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.SQL.SQL_Expression expression;
		public SQL_Expression expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<SQL_CaseWhenClause> whenThens;
		public TokenList<SQL_CaseWhenClause> whenThens;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT SQL_CaseElseClause elseClause;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.SQL.Terminals.SQL_Keyword END = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("END");
		public SQL_Keyword END = new SQL_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class SQL_CaseWhenClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword WHEN = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("WHEN");
			public SQL_Keyword WHEN = new SQL_Keyword("WHEN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.SQL_Expression whenExpression;
			public SQL_Expression whenExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword THEN = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("THEN");
			public SQL_Keyword THEN = new SQL_Keyword("THEN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.SQL.SQL_Expression thenExpression;
			public SQL_Expression thenExpression;
		}

		public class SQL_CaseElseClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword ELSE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("ELSE");
			public SQL_Keyword ELSE = new SQL_Keyword("ELSE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.SQL_Expression elseExpression;
			public SQL_Expression elseExpression;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
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
				bool matches = value == whenValue;
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

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression newExpr = transformer.transformExpression(generator, expression);
			SQL_Identifier_Reference id = var.variable.ids.first();

			List<AbstractExpression> values = new List<AbstractExpression>();
			List<List<AbstractStatement>> cases = new List<List<AbstractStatement>>();
			for (int i = 0; i < whenThens.size(); i++)
			{
				SQL_CaseWhenClause when = whenThens._elements.get(i);
				List<AbstractStatement> thisCase = new List<AbstractStatement>();
				values.Add(transformer.transformExpression(generator, when.whenExpression));

				AbstractExpression thisValue = transformer.transformExpression(generator, when.thenExpression);
				AbstractExpression thisAsgExpr = generator.newAssignmentExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.EQUALS, thisValue, when);
				thisCase.Add(generator.newExpressionStatement(thisAsgExpr, when));

				cases.Add(thisCase);
			}

			List<AbstractStatement> defaultCase = null;
			if (elseClause != null && elseClause.isPresent())
			{
				defaultCase = new List<AbstractStatement>();
				AbstractExpression defaultValue = transformer.transformExpression(generator, elseClause.elseExpression);
				AbstractExpression defaultAsgExpr = generator.newAssignmentExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.EQUALS, defaultValue, elseClause);
				defaultCase.Add(generator.newExpressionStatement(defaultAsgExpr, elseClause));
			}

			AbstractStatement stmt = generator.newSwitchStatement(newExpr, values, cases, defaultCase, this);
			return stmt;
		}
	}

}
