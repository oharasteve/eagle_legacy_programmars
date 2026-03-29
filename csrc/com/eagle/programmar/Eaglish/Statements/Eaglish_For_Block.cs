// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

namespace com.eagle.programmar.Eaglish.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Eaglish_Expression = com.eagle.programmar.Eaglish.Eaglish_Expression;
	using Eaglish_Statement = com.eagle.programmar.Eaglish.Eaglish_Statement;
	using Eaglish_Variable_Definition = com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition;
	using Eaglish_EndOfLine = com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
	using Eaglish_Keyword = com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
	using Eaglish_KeywordChoice = com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
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

	public class Eaglish_For_Block : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword FOR = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("FOR");
		public Eaglish_Keyword FOR = new Eaglish_Keyword("FOR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition variable;
		public Eaglish_Variable_Definition variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Eaglish.Eaglish_Expression startValue;
		public Eaglish_Expression startValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice TO = new com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice("TO", "DOWN_TO");
		public Eaglish_KeywordChoice TO = new Eaglish_KeywordChoice("TO", "DOWN_TO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Eaglish.Eaglish_Expression stopValue;
		public Eaglish_Expression stopValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln1;
		public Eaglish_EndOfLine eoln1;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<com.eagle.programmar.Eaglish.Eaglish_Statement> statements;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword END_FOR = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("END_FOR");
		public Eaglish_Keyword END_FOR = new Eaglish_Keyword("END_FOR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln2;
		public Eaglish_EndOfLine eoln2;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
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
			bool backwards = which.Equals("DOWN_TO");

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
				interpreter.setSymbol(variable, variable.ToString(), new EagleInteger(i));

				foreach (Eaglish_Statement stmt in statements._elements)
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

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression initExpr = transformer.transformExpression(generator, startValue);
			AbstractExpression termExpr = transformer.transformExpression(generator, stopValue);
			AbstractExpression incrExpr;
			EagleGenerator.RelationalEnum relOp;

			switch (TO.getValue())
			{
			case "TO":
				incrExpr = generator.newNumberExpression("1", TO);
				relOp = EagleGenerator.RelationalEnum.LESS_EQUALS;
				break;
			case "DOWN_TO":
				incrExpr = generator.newNumberExpression("-1", TO);
				relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
				break;
			default:
				throw new Exception("Unexpected direction: " + TO.getValue());
			}

			List<AbstractStatement> actionList = new List<AbstractStatement>();
			foreach (Eaglish_Statement statement in statements._elements)
			{
				List<AbstractStatement> stmts = transformer.transformStatement(generator, statement.getWhich());
				if (stmts != null)
				{
					foreach (AbstractStatement stmt in stmts)
					{
						actionList.Add(stmt);
					}
				}
			}

			AbstractVariable var = generator.newVariable(variable.getValue());
			AbstractStatement stmt = generator.newForRangeStatement(var, EagleGenerator.TypeEnum.VOID, initExpr, relOp, termExpr, incrExpr, actionList, this);
			return stmt;
		}
	}

}
