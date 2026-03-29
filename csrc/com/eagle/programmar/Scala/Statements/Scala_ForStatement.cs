// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Scala.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Scala_Expression = com.eagle.programmar.Scala.Scala_Expression;
	using Scala_Statement = com.eagle.programmar.Scala.Scala_Statement;
	using Scala_Variable = com.eagle.programmar.Scala.Scala_Variable;
	using Scala_ParenthesizedExpression = com.eagle.programmar.Scala.Expressions.Scala_ParenthesizedExpression;
	using Scala_RangeExpression = com.eagle.programmar.Scala.Expressions.Scala_RangeExpression;
	using Scala_ReverseMethod = com.eagle.programmar.Scala.Functions.Scala_ReverseMethod;
	using Scala_Keyword = com.eagle.programmar.Scala.Terminals.Scala_Keyword;
	using Scala_Punctuation = com.eagle.programmar.Scala.Terminals.Scala_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Scala_ForStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("taste-control-structures.html#for-loops-and-expressions") com.eagle.programmar.Scala.Terminals.Scala_Keyword FOR = new com.eagle.programmar.Scala.Terminals.Scala_Keyword("for");
		public @DOC("taste-control-structures.html#for-loops-and-expressions") Scala_Keyword FOR = new Scala_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Scala.Scala_Variable variable;
		public Scala_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Scala.Terminals.Scala_Punctuation arrow = new com.eagle.programmar.Scala.Terminals.Scala_Punctuation("<-");
		public Scala_Punctuation arrow = new Scala_Punctuation("<-");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Scala.Scala_Expression values;
		public Scala_Expression values;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Scala.Scala_Statement statement;
		public Scala_Statement statement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			AbstractToken which = values.getWhich();
			Scala_RangeExpression range = null;
			bool backwards = false;
			int start = 0;
			int stop = 0;
			if (which is Scala_RangeExpression)
			{
				range = (Scala_RangeExpression) which;
				start = interpreter.getIntValue(range.left);
				stop = interpreter.getIntValue(range.right);
			}
			if (which is Scala_ReverseMethod)
			{
				Scala_ReverseMethod reversed = (Scala_ReverseMethod) which;
				if (reversed.leftExpr.getWhich() is Scala_ParenthesizedExpression)
				{
					Scala_ParenthesizedExpression parens = (Scala_ParenthesizedExpression) reversed.leftExpr.getWhich();
					if (parens.expression.getWhich() is Scala_RangeExpression)
					{
						range = (Scala_RangeExpression) parens.expression.getWhich();
						backwards = true;
						start = interpreter.getIntValue(range.right);
						stop = interpreter.getIntValue(range.left);
					}
				}
			}
			if (range == null)
			{
				throw new Exception("FOR statement requires a Range of values");
			}

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

			int i = start;
			while (true)
			{
				if (!backwards && i > stop)
				{
					break;
				}
				if (backwards && i < stop)
				{
					break;
				}

				metric.iterate();
				interpreter.setSymbol(variable, variable.vars.first().getValue(), new EagleInteger(i));

				result = interpreter.tryToInterpret(statement);

				if (result == Eagle_Statement_Result.BREAK)
				{
					metric.broke();
					result = Eagle_Statement_Result.NORMAL;
					break;
				}
				else if (result == Eagle_Statement_Result.CONTINUE)
				{
					metric.continued();
					result = Eagle_Statement_Result.NORMAL;
				}
				else if (result == Eagle_Statement_Result.RETURN)
				{
					break;
				}

				if (backwards)
				{
					i--;
				}
				else
				{
					i++;
				}
			}

			_metrics.competedLoop(metric, backwards);
			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractToken which = values.getWhich();
			Scala_RangeExpression range = null;
			AbstractExpression initExpr = null;
			AbstractExpression termExpr = null;
			AbstractExpression incrExpr = null;
			EagleGenerator.RelationalEnum relOp = EagleGenerator.RelationalEnum.LESS_EQUALS;
			if (which is Scala_RangeExpression)
			{
				range = (Scala_RangeExpression) which;
				initExpr = transformer.transformExpression(generator, range.left);
				termExpr = transformer.transformExpression(generator, range.right);
			}
			if (which is Scala_ReverseMethod)
			{
				Scala_ReverseMethod reversed = (Scala_ReverseMethod) which;
				if (reversed.leftExpr.getWhich() is Scala_ParenthesizedExpression)
				{
					Scala_ParenthesizedExpression parens = (Scala_ParenthesizedExpression) reversed.leftExpr.getWhich();
					if (parens.expression.getWhich() is Scala_RangeExpression)
					{
						range = (Scala_RangeExpression) parens.expression.getWhich();
						initExpr = transformer.transformExpression(generator, range.right);
						termExpr = transformer.transformExpression(generator, range.left);
						incrExpr = generator.newNumberExpression("-1", null);
						relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
					}
				}
			}
			if (range == null)
			{
				throw new Exception("FOR statement requires a Range of values");
			}

			List<AbstractStatement> newStmts = Scala_BlockStatement.collectStatements(transformer, generator, statement);
			List<AbstractStatement> actionList = new List<AbstractStatement>();
			if (newStmts != null)
			{
				foreach (AbstractStatement stmt in newStmts)
				{
					actionList.Add(stmt);
				}
			}

			AbstractVariable var = generator.newVariable(variable.vars.first().getValue());
			return generator.newForRangeStatement(var, EagleGenerator.TypeEnum.INTEGER, initExpr, relOp, termExpr, incrExpr, actionList, this);
		}
	}

}
