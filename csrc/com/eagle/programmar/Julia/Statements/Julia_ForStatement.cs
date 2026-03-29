// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Julia.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Julia_Expression = com.eagle.programmar.Julia.Julia_Expression;
	using Julia_Statement = com.eagle.programmar.Julia.Julia_Statement;
	using Julia_Variable = com.eagle.programmar.Julia.Julia_Variable;
	using Julia_NegativeExpression = com.eagle.programmar.Julia.Expressions.Julia_NegativeExpression;
	using Julia_RangeExpression = com.eagle.programmar.Julia.Expressions.Julia_RangeExpression;
	using Julia_EOLN = com.eagle.programmar.Julia.Terminals.Julia_EOLN;
	using Julia_Keyword = com.eagle.programmar.Julia.Terminals.Julia_Keyword;
	using Julia_Number = com.eagle.programmar.Julia.Terminals.Julia_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Julia_ForStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("manual/control-flow/#man-loops") com.eagle.programmar.Julia.Terminals.Julia_Keyword FOR = new com.eagle.programmar.Julia.Terminals.Julia_Keyword("for");
		public @DOC("manual/control-flow/#man-loops") Julia_Keyword FOR = new Julia_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Julia.Julia_Variable var;
		public Julia_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Julia_ForOperator oper;
		public Julia_ForOperator oper;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Julia.Julia_Expression values;
		public Julia_Expression values;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Julia.Terminals.Julia_EOLN eoln1;
		public Julia_EOLN eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<com.eagle.programmar.Julia.Julia_Statement> statements;
		public TokenList<Julia_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Julia.Terminals.Julia_Keyword END = new com.eagle.programmar.Julia.Terminals.Julia_Keyword("end");
		public Julia_Keyword END = new Julia_Keyword("end");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Julia.Terminals.Julia_EOLN eoln2;
		public Julia_EOLN eoln2;

		public static class Julia_ForOperator extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_Keyword XXIN = new com.eagle.programmar.Julia.Terminals.Julia_Keyword("in");
			public Julia_Keyword XXIN = new Julia_Keyword("in");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationEquals XXequals;
			public PunctuationEquals XXequals;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (!(values.getWhich() is Julia_RangeExpression))
			{
				throw new Exception("FOR statement requires a Range of values");
			}

			Julia_RangeExpression range = (Julia_RangeExpression) values.getWhich();
			int start = interpreter.getIntValue(range.first);
			int stop = interpreter.getIntValue(range.lastOrIncrement);
			int incr = 1;
			if (range.hasIncr != null && range.hasIncr.isPresent())
			{
				incr = stop;
				stop = interpreter.getIntValue(range.hasIncr.last);
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
				if (incr > 0 && i > stop)
				{
					break;
				}
				if (incr < 0 && i < stop)
				{
					break;
				}

				metric.iterate();
				interpreter.setSymbol(var, var.vars.first().getValue(), new EagleInteger(i));

				foreach (Julia_Statement stmt in statements._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}

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

				i += incr;
			}

			_metrics.competedLoop(metric, incr < 0);
			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractToken which = values.getWhich();
			Julia_RangeExpression range = null;
			AbstractExpression initExpr = null;
			AbstractExpression termExpr = null;
			AbstractExpression incrExpr = null;
			EagleGenerator.RelationalEnum relOp = EagleGenerator.RelationalEnum.LESS_EQUALS;

			if (!(which is Julia_RangeExpression))
			{
				throw new Exception("FOR statement requires a Range of values, not " + which);
			}
			range = (Julia_RangeExpression) which;

			initExpr = transformer.transformExpression(generator, range.first);
			if (range.hasIncr != null && range.hasIncr.isPresent())
			{
				AbstractToken which2 = range.lastOrIncrement.getWhich();
				if (which2 is Julia_NegativeExpression)
				{
					relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
				}
				else if (which2 is Julia_Number)
				{
					Julia_Number num = (Julia_Number) which2;
					if (num.getValue().StartsWith("-"))
					{
						relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
					}
				}
				incrExpr = transformer.transformExpression(generator, range.lastOrIncrement);
				termExpr = transformer.transformExpression(generator, range.hasIncr.last);
			}
			else
			{
				termExpr = transformer.transformExpression(generator, range.lastOrIncrement);
			}

			List<AbstractStatement> actionList = new List<AbstractStatement>();
			foreach (Julia_Statement stmt1 in statements._elements)
			{
				List<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt1);
				if (newStmts != null)
				{
					foreach (AbstractStatement stmt2 in newStmts)
					{
						actionList.Add(stmt2);
					}
				}
			}

			AbstractVariable newVar = generator.newVariable(var.vars.first().getValue());
			return generator.newForRangeStatement(newVar, EagleGenerator.TypeEnum.VOID, initExpr, relOp, termExpr, incrExpr, actionList, this);
		}
	}

}
