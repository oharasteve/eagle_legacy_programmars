// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.FSharp.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using FSharp_SingleOrMultiLineStatement = com.eagle.programmar.FSharp.FSharp_Element.FSharp_SingleOrMultiLineStatement;
	using FSharp_Expression = com.eagle.programmar.FSharp.FSharp_Expression;
	using FSharp_Variable = com.eagle.programmar.FSharp.FSharp_Variable;
	using FSharp_Keyword = com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
	using FSharp_KeywordChoice = com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice;
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

	public class FSharp_ForStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("loops-for-to-expression") com.eagle.programmar.FSharp.Terminals.FSharp_Keyword FOR = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("for");
		public @DOC("loops-for-to-expression") FSharp_Keyword FOR = new FSharp_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.FSharp_Variable var;
		public FSharp_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.FSharp.FSharp_Expression startValue;
		public FSharp_Expression startValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice TO = new com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice("to", "downto");
		public FSharp_KeywordChoice TO = new FSharp_KeywordChoice("to", "downto");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.FSharp.FSharp_Expression stopValue;
		public FSharp_Expression stopValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.FSharp.Terminals.FSharp_Keyword DO = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("do");
		public FSharp_Keyword DO = new FSharp_Keyword("do");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.FSharp.FSharp_Element.FSharp_SingleOrMultiLineStatement forActions;
		public FSharp_SingleOrMultiLineStatement forActions;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			int start = interpreter.getIntValue(startValue);
			int stop = interpreter.getIntValue(stopValue);

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			string which = TO.getValue();
			bool backwards = which.Equals("downto");

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
				interpreter.setSymbol(var, var.id.getValue(), new EagleInteger(i));

				result = interpreter.tryToInterpret(forActions);

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
			AbstractExpression initExpr = transformer.transformExpression(generator, startValue);
			AbstractExpression termExpr = transformer.transformExpression(generator, stopValue);
			AbstractExpression incrExpr;
			EagleGenerator.RelationalEnum relOp;

			switch (TO.getValue())
			{
			case "to":
				incrExpr = generator.newNumberExpression("1", TO);
				relOp = EagleGenerator.RelationalEnum.LESS_EQUALS;
				break;
			case "downto":
				incrExpr = generator.newNumberExpression("-1", TO);
				relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
				break;
			default:
				throw new Exception("Unexpected direction: " + TO.getValue());
			}

			List<AbstractStatement> actionList = new List<AbstractStatement>();
			List<AbstractStatement> stmts = transformer.transformStatement(generator, forActions.getWhich());
			if (stmts != null)
			{
				foreach (AbstractStatement stmt in stmts)
				{
					actionList.Add(stmt);
				}
			}

			AbstractVariable newVar = generator.newVariable(var.id.getValue());
			AbstractStatement stmt = generator.newForRangeStatement(newVar, EagleGenerator.TypeEnum.VOID, initExpr, relOp, termExpr, incrExpr, actionList, this);
			return stmt;
		}
	}

}
