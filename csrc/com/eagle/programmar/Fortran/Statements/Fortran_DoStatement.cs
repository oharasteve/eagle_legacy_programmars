// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

namespace com.eagle.programmar.Fortran.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Fortran_Expression = com.eagle.programmar.Fortran.Fortran_Expression;
	using Fortran_Statement = com.eagle.programmar.Fortran.Fortran_Statement;
	using Fortran_Variable_Reference = com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
	using Fortran_EOLN = com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
	using Fortran_Keyword = com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
	using Fortran_Number = com.eagle.programmar.Fortran.Terminals.Fortran_Number;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Fortran_DoStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("6j4m0vn8c/index.html") com.eagle.programmar.Fortran.Terminals.Fortran_Keyword DO1 = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("DO");
		public @DOC("6j4m0vn8c/index.html") Fortran_Keyword DO1 = new Fortran_Keyword("DO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference var;
		public Fortran_Variable_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Fortran.Fortran_Expression startValue;
		public Fortran_Expression startValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationComma comma;
		public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Fortran.Fortran_Expression stopValue;
		public Fortran_Expression stopValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Fortran_DoIncrement incrValue;
		public @OPT Fortran_DoIncrement incrValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Fortran.Terminals.Fortran_EOLN eoln1;
		public Fortran_EOLN eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.TokenList<com.eagle.programmar.Fortran.Fortran_Statement> statements;
		public TokenList<Fortran_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.Fortran.Terminals.Fortran_Keyword END = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("END");
		public Fortran_Keyword END = new Fortran_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.programmar.Fortran.Terminals.Fortran_Keyword DO2 = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("DO");
		public Fortran_Keyword DO2 = new Fortran_Keyword("DO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) com.eagle.programmar.Fortran.Terminals.Fortran_EOLN eoln2;
		public Fortran_EOLN eoln2;

		public static class Fortran_DoIncrement extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Fortran.Fortran_Expression incr;
			public Fortran_Expression incr;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			int start = interpreter.getIntValue(startValue);
			int stop = interpreter.getIntValue(stopValue);
			int incr = 1;
			if (incrValue != null && incrValue.isPresent())
			{
				incr = interpreter.getIntValue(incrValue.incr);
			}

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, DO1);
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
				interpreter.setSymbol(var, var.ToString(), new EagleInteger(i));

				foreach (Fortran_Statement stmt in statements._elements)
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
			AbstractVariable loopVar = null;
			AbstractExpression startExpr = null;
			AbstractExpression stopExpr = null;
			AbstractExpression incrExpr = null;
			EagleGenerator.RelationalEnum relOp = EagleGenerator.RelationalEnum.LESS_EQUALS;

			loopVar = generator.newVariable(var.getValue());
			startExpr = transformer.transformExpression(generator, startValue);
			stopExpr = transformer.transformExpression(generator, stopValue);
			if (incrValue != null && incrValue.isPresent())
			{
				incrExpr = transformer.transformExpression(generator, incrValue.incr);
				if (incrValue.incr.getWhich() is Fortran_Number)
				{
					Fortran_Number number = (Fortran_Number) incrValue.incr.getWhich();
					if (number.getValue().StartsWith("-"))
					{
						// What if it is a variable that happens to be negative? Yikes!
						relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
					}
				}
			}

			List<AbstractStatement> newStmts = new List<AbstractStatement>();
			foreach (Fortran_Statement stmt in statements._elements)
			{
				AbstractStatement newStmt = transformer.transformStatement1(generator, stmt.getWhich());
				newStmts.Add(newStmt);
			}

			// And now generate the output code
			return generator.newForRangeStatement(loopVar, EagleGenerator.TypeEnum.VOID, startExpr, relOp, stopExpr, incrExpr, newStmts, this);
		}
	}

}
