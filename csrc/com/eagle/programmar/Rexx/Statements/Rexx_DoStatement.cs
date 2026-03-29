// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Rexx_Element = com.eagle.programmar.Rexx.Rexx_Element;
	using Rexx_Expression = com.eagle.programmar.Rexx.Rexx_Expression;
	using Rexx_Identifier_Reference = com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference;
	using Rexx_EndOfLine = com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine;
	using Rexx_Keyword = com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
	using Rexx_Number = com.eagle.programmar.Rexx.Terminals.Rexx_Number;
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

	public class Rexx_DoStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("instructions-do") com.eagle.programmar.Rexx.Terminals.Rexx_Keyword DO = new com.eagle.programmar.Rexx.Terminals.Rexx_Keyword("DO");
		public @DOC("instructions-do") Rexx_Keyword DO = new Rexx_Keyword("DO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Rexx_DoLoop loop;
		public @OPT Rexx_DoLoop loop;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Rexx_DoWhile doWhile;
		public @OPT Rexx_DoWhile doWhile;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine eoln1;
		public Rexx_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<com.eagle.programmar.Rexx.Rexx_Element> actions;
		public TokenList<Rexx_Element> actions;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Rexx.Terminals.Rexx_Keyword END = new com.eagle.programmar.Rexx.Terminals.Rexx_Keyword("END");
		public Rexx_Keyword END = new Rexx_Keyword("END");

		public static class Rexx_DoLoop extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference var;
			public Rexx_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rexx.Rexx_Expression from;
			public Rexx_Expression from;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Rexx.Terminals.Rexx_Keyword TO = new com.eagle.programmar.Rexx.Terminals.Rexx_Keyword("TO");
			public Rexx_Keyword TO = new Rexx_Keyword("TO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Rexx.Rexx_Expression to;
			public Rexx_Expression to;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Rexx_DoBy by;
			public @OPT Rexx_DoBy by;

			public static class Rexx_DoBy extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rexx.Terminals.Rexx_Keyword BY = new com.eagle.programmar.Rexx.Terminals.Rexx_Keyword("BY");
				public Rexx_Keyword BY = new Rexx_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rexx.Rexx_Expression step;
				public Rexx_Expression step;
			}
		}

		public static class Rexx_DoWhile extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rexx.Terminals.Rexx_Keyword WHILE = new com.eagle.programmar.Rexx.Terminals.Rexx_Keyword("WHILE");
			public Rexx_Keyword WHILE = new Rexx_Keyword("WHILE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rexx.Rexx_Expression condition;
			public Rexx_Expression condition;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

			// Just a DO ... END block, no iteration
			if ((loop == null || !loop.isPresent()) && (doWhile == null || !doWhile.isPresent()))
			{
				foreach (Rexx_Element stmt in actions._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}
				return result;
			}

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, DO);
			}
			ForLoopMetric metric = new ForLoopMetric();

			int start = 0;
			int stop = 0;
			int by = 0;
			int current = 0;

			if (loop != null && loop.isPresent())
			{
				start = interpreter.getIntValue(loop.from);
				interpreter.setSymbol(this, loop.var.getValue(), new EagleInteger(start));

				current = interpreter.getIntValue(loop.from);
				stop = interpreter.getIntValue(loop.to);
				by = 1;

				if (loop.by != null && loop.by.isPresent())
				{
					by = interpreter.getIntValue(loop.by.step);
				}
			}

			while (true)
			{
				if (doWhile != null && doWhile.isPresent())
				{
					bool keepGoing = interpreter.getBoolValue(doWhile.condition);
					if (!keepGoing)
					{
						break;
					}
				}

				if (loop != null && loop.isPresent())
				{
					if (by < 0)
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
				}

				metric.iterate();
				interpreter.setSymbol(this, loop.var.getValue(), new EagleInteger(current));

				foreach (Rexx_Element stmt in actions._elements)
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

				if (loop != null && loop.isPresent())
				{
					current += by;
				}
			}

			_metrics.competedLoop(metric, by < 0);
			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (loop != null && loop.isPresent())
			{
				if (doWhile != null && doWhile.isPresent())
				{
					throw new Exception("Need to implement DO LOOP with WHILE");
				}

				EagleGenerator.RelationalEnum relOp = EagleGenerator.RelationalEnum.LESS_EQUALS;
				AbstractExpression initExpr = transformer.transformExpression(generator, loop.from);
				AbstractExpression termExpr = transformer.transformExpression(generator, loop.to);
				AbstractExpression incrExpr = null;
				if (loop.by != null && loop.by.isPresent())
				{
					incrExpr = transformer.transformExpression(generator, loop.by.step);
					if (loop.by.step.getWhich() is Rexx_Number)
					{
						Rexx_Number number = (Rexx_Number) loop.by.step.getWhich();
						if (number.getValue().StartsWith("-"))
						{
							// What if it is a variable that happens to be negative? Yikes!
							relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
						}
					}
				}

				List<AbstractStatement> actionList = new List<AbstractStatement>();
				foreach (Rexx_Element statement in actions._elements)
				{
					List<AbstractStatement> stmts = transformer.transformStatement(generator, statement.baseStatement.getWhich());
					if (stmts != null)
					{
						foreach (AbstractStatement stmt in stmts)
						{
							actionList.Add(stmt);
						}
					}
				}

				AbstractVariable var = generator.newVariable(loop.var.getValue());
				AbstractStatement stmt = generator.newForRangeStatement(var, EagleGenerator.TypeEnum.VOID, initExpr, relOp, termExpr, incrExpr, actionList, this);
				return stmt;
			}

			if (doWhile != null && doWhile.isPresent())
			{
				AbstractExpression cond = transformer.transformExpression(generator, doWhile.condition);
				List<AbstractStatement> whileTrue = new List<AbstractStatement>();

				foreach (Rexx_Element statement in actions._elements)
				{
					foreach (AbstractStatement stmt in transformer.transformStatement(generator, statement.baseStatement.getWhich()))
					{
						whileTrue.Add(stmt);
					}
				}

				AbstractStatement stmt = generator.newWhileStatement(cond, whileTrue, this);
				return stmt;
			}

			List<AbstractStatement> stmts = new List<AbstractStatement>();
			if (this.actions != null)
			{
				foreach (Rexx_Element elt in this.actions._elements)
				{
					AbstractStatement newStmt = transformer.transformStatement1(generator, elt.baseStatement.getWhich());
					if (newStmt != null)
					{
						stmts.Add(newStmt);
					}
				}
			}

			return generator.newBlockStatement(stmts, this);
		}
	}

}
