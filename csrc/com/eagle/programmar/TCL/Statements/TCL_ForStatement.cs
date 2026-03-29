// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

namespace com.eagle.programmar.TCL.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using TCL_Statement = com.eagle.programmar.TCL.TCL_Element.TCL_Statement;
	using TCL_Expression = com.eagle.programmar.TCL.TCL_Expression;
	using TCL_RelationalExpression = com.eagle.programmar.TCL.Expressions.TCL_RelationalExpression;
	using TCL_Keyword = com.eagle.programmar.TCL.Terminals.TCL_Keyword;
	using TCL_Number = com.eagle.programmar.TCL.Terminals.TCL_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class TCL_ForStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("TclCmd/for.html") com.eagle.programmar.TCL.Terminals.TCL_Keyword FOR = new com.eagle.programmar.TCL.Terminals.TCL_Keyword("for");
		public @DOC("TclCmd/for.html") TCL_Keyword FOR = new TCL_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace1;
		public PunctuationLeftBrace leftBrace1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) TCL_SetStatement initialize;
		public TCL_SetStatement initialize;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace1;
		public PunctuationRightBrace rightBrace1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace2;
		public PunctuationLeftBrace leftBrace2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.TCL.TCL_Expression condition;
		public TCL_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace2;
		public PunctuationRightBrace rightBrace2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace3;
		public PunctuationLeftBrace leftBrace3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) TCL_IncrStatement increment;
		public TCL_IncrStatement increment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace3;
		public PunctuationRightBrace rightBrace3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.programmar.TCL.TCL_Element.TCL_Statement action;
		public TCL_Statement action;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(initialize);

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				bool keepGoing = interpreter.getBoolValue(condition);
				if (!keepGoing)
				{
					break;
				}

				metric.iterate();

				result = interpreter.tryToInterpret(action);

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

				interpreter.tryToInterpret(increment);
			}

			// Have to guess to see if it was backwards
			bool backwards = guessDirection(condition, increment);

			_metrics.competedLoop(metric, backwards);
			return result;
		}

		private static bool guessDirection(TCL_Expression testExpr, TCL_IncrStatement incrStmt)
		{
			if (incrStmt.amount != null && incrStmt.amount.isPresent())
			{
				AbstractToken which1 = incrStmt.amount.getWhich();
				if (which1 is TCL_Number)
				{
					TCL_Number num = (TCL_Number) which1;
					if (num.getValue().StartsWith("-"))
					{
						return true;
					}
				}
			}

			AbstractToken which2 = testExpr.getWhich();
			if (which2 is TCL_RelationalExpression)
			{
				TCL_RelationalExpression rel = (TCL_RelationalExpression) which2;
				string oper = rel.@operator.getWhich().ToString().ToLower();
				if (oper.Equals(">") || oper.Equals(">=") || oper.Equals("gt") || oper.Equals("ge"))
				{
					return true;
				}
			}

			return false; // Just don't know :(
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression initExpr = initialize.transformExpression(transformer, generator);
			AbstractExpression termCond = transformer.transformExpression(generator, condition);
			AbstractExpression incrExpr = increment.transformExpression(transformer, generator);

			List<AbstractStatement> whileTrue = new List<AbstractStatement>();

			List<AbstractStatement> stmts = transformer.transformStatement(generator, action.getWhich());
			if (stmts != null)
			{
				foreach (AbstractStatement stmt in stmts)
				{
					whileTrue.Add(stmt);
				}
			}

			return generator.newForLoopStatement(initExpr, termCond, incrExpr, whileTrue, this);
		}
	}

}
