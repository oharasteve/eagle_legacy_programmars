// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

namespace com.eagle.programmar.VB.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using VB_Element = com.eagle.programmar.VB.VB_Element;
	using VB_Statement = com.eagle.programmar.VB.VB_Element.VB_Statement;
	using VB_Expression = com.eagle.programmar.VB.VB_Expression;
	using VB_Identifier_Reference = com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
	using VB_EndOfLine = com.eagle.programmar.VB.Terminals.VB_EndOfLine;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using VB_Number = com.eagle.programmar.VB.Terminals.VB_Number;
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

	public class VB_ForStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("statements/for-next-statement") com.eagle.programmar.VB.Terminals.VB_Keyword FOR = new com.eagle.programmar.VB.Terminals.VB_Keyword("for");
		public @DOC("statements/for-next-statement") VB_Keyword FOR = new VB_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.Symbols.VB_Identifier_Reference variable;
		public VB_Identifier_Reference variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.VB.VB_Expression from;
		public VB_Expression from;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.VB.Terminals.VB_Keyword TO = new com.eagle.programmar.VB.Terminals.VB_Keyword("to");
		public VB_Keyword TO = new VB_Keyword("to");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.VB.VB_Expression to;
		public VB_Expression to;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT VB_ForStep by;
		public @OPT VB_ForStep by;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.VB.Terminals.VB_EndOfLine eoln;
		public VB_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.TokenList<com.eagle.programmar.VB.VB_Element> actions;
		public TokenList<VB_Element> actions;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.VB.Terminals.VB_Keyword NEXT = new com.eagle.programmar.VB.Terminals.VB_Keyword("next");
		public VB_Keyword NEXT = new VB_Keyword("next");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT VB_Identifier_Reference var2;
		public @OPT VB_Identifier_Reference var2;

		public static class VB_ForStep extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_Keyword STEP = new com.eagle.programmar.VB.Terminals.VB_Keyword("step");
			public VB_Keyword STEP = new VB_Keyword("step");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.VB_Expression step;
			public VB_Expression step;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			int current = interpreter.getIntValue(from);
			int stop = interpreter.getIntValue(to);
			int incr = 1;

			if (by != null && by.isPresent())
			{
				incr = interpreter.getIntValue(by.step);
			}

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				if (incr < 0)
				{
					if (current < stop)
					{
						break;
					}
				}
				else
				{
					if (current > stop)
					{
						break;
					}
				}

				metric.iterate();

				interpreter.setSymbol(variable, variable.getValue(), new EagleInteger(current));

				foreach (VB_Element stmt in actions._elements)
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

				current += incr;
			}

			_metrics.competedLoop(metric, incr < 0);
			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression initExpr = transformer.transformExpression(generator, from);
			AbstractExpression termExpr = transformer.transformExpression(generator, to);
			AbstractExpression incrExpr = null;
			EagleGenerator.RelationalEnum relOp = EagleGenerator.RelationalEnum.LESS_EQUALS;
			if (by != null && by.isPresent())
			{
				incrExpr = transformer.transformExpression(generator, by.step);
				if (by.step != null && by.step.isPresent())
				{
					incrExpr = transformer.transformExpression(generator, by.step);
					if (by.step.getWhich() is VB_Number)
					{
						VB_Number number = (VB_Number) by.step.getWhich();
						if (number.getValue().StartsWith("-"))
						{
							// What it is a variable that happens to be negative? Yikes!
							relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
						}
					}
				}
			}

			List<AbstractStatement> actionList = new List<AbstractStatement>();
			foreach (VB_Element statement in actions._elements)
			{
				for (int i = 0; i < statement.baseStatements.getPrimaryCount(); i++)
				{
					VB_Element.VB_Statement baseStatement = statement.baseStatements.getPrimaryElement(i);
					List<AbstractStatement> stmts = transformer.transformStatement(generator, baseStatement.getWhich());
					if (stmts != null)
					{
						foreach (AbstractStatement stmt in stmts)
						{
							actionList.Add(stmt);
						}
					}
				}
			}

			AbstractVariable var = generator.newVariable(variable.getValue());
			AbstractStatement stmt = generator.newForRangeStatement(var, EagleGenerator.TypeEnum.VOID, initExpr, relOp, termExpr, incrExpr, actionList, this);
			return stmt;
		}
	}

}
