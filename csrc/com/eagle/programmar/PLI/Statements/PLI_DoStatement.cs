// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 18, 2011

namespace com.eagle.programmar.PLI.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using PLI_Expression = com.eagle.programmar.PLI.PLI_Expression;
	using PLI_Label = com.eagle.programmar.PLI.PLI_Label;
	using PLI_StatementOrComment = com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
	using PLI_Statement = com.eagle.programmar.PLI.PLI_Statement;
	using PLI_Identifier_Reference = com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
	using PLI_Keyword = com.eagle.programmar.PLI.Terminals.PLI_Keyword;
	using PLI_Number = com.eagle.programmar.PLI.Terminals.PLI_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class PLI_DoStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PLI_Label label1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("7.15") com.eagle.programmar.PLI.Terminals.PLI_Keyword DO = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("DO");
		public @DOC("7.15") PLI_Keyword DO = new PLI_Keyword("DO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PLI_DoLoop doLoop;
		public @OPT PLI_DoLoop doLoop;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PLI_DoUntil doUntil;
		public @OPT PLI_DoUntil doUntil;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT PLI_DoWhile doWhile;
		public @OPT PLI_DoWhile doWhile;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT PLI_Keyword FOREVER = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("FOREVER");
		public @OPT PLI_Keyword FOREVER = new PLI_Keyword("FOREVER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon1;
		public PunctuationSemicolon semicolon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment> statements;
		public @OPT TokenList<PLI_StatementOrComment> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.PLI.Terminals.PLI_Keyword END = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("END");
		public PLI_Keyword END = new PLI_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT PLI_Identifier_Reference label2;
		public @OPT PLI_Identifier_Reference label2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon2;
		public PunctuationSemicolon semicolon2;

		public static class PLI_DoLoop extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference id;
			public PLI_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PLI.PLI_Expression fromExpr;
			public PLI_Expression fromExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.PLI.Terminals.PLI_Keyword TO = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("TO");
			public PLI_Keyword TO = new PLI_Keyword("TO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.PLI.PLI_Expression toExpr;
			public PLI_Expression toExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT PLI_DoBy by;
			public @OPT PLI_DoBy by;

			public static class PLI_DoBy extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword BY = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("BY");
				public PLI_Keyword BY = new PLI_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.PLI_Expression byExpr;
				public PLI_Expression byExpr;
			}
		}

		public static class PLI_DoUntil extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword UNTIL = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("UNTIL");
			public PLI_Keyword UNTIL = new PLI_Keyword("UNTIL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.PLI_Expression condition;
			public PLI_Expression condition;
		}

		public static class PLI_DoWhile extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword WHILE = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("WHILE");
			public PLI_Keyword WHILE = new PLI_Keyword("WHILE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.PLI_Expression condition;
			public PLI_Expression condition;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			bool simpleDo = true;
			PLI_Expression whileCond = null;
			PLI_Expression untilCond = null;
			PLI_Identifier_Reference loopVar = null;
			int start = 0;
			int stop = 0;
			int step = 1;
			bool hasLoop = false;

			if (doLoop != null && doLoop.isPresent())
			{
				hasLoop = true;
				loopVar = doLoop.id;
				start = interpreter.getIntValue(doLoop.fromExpr);
				stop = interpreter.getIntValue(doLoop.toExpr);
				if (doLoop.by != null && doLoop.by.isPresent())
				{
					step = interpreter.getIntValue(doLoop.by.byExpr);
				}
				simpleDo = false;
			}
			if (doWhile != null && doWhile.isPresent())
			{
				whileCond = doWhile.condition;
				simpleDo = false;
			}
			if (doUntil != null && doUntil.isPresent())
			{
				untilCond = doUntil.condition;
				simpleDo = false;
			}
			if (FOREVER != null && FOREVER.isPresent())
			{
				simpleDo = false;
			}
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

			if (simpleDo)
			{
				// No iteration, no metrics, no logic, just a groups of statements
				foreach (PLI_StatementOrComment stmt in statements._elements)
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

			int i = start;
			while (true)
			{
				if (whileCond != null)
				{
					bool cond = interpreter.getBoolValue(whileCond);
					if (!cond)
					{
						break;
					}
				}
				if (hasLoop)
				{
					if (step > 0 && i > stop)
					{
						break;
					}
					if (step < 0 && i < stop)
					{
						break;
					}
					interpreter.setSymbol(loopVar, loopVar.getValue(), new EagleInteger(i));
				}

				metric.iterate();

				foreach (PLI_StatementOrComment stmt in statements._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}

				if (result == Eagle_Statement_Result.RETURN)
				{
					break;
				}

				if (hasLoop)
				{
					i += step;
				}
				if (untilCond != null)
				{
					bool cond = interpreter.getBoolValue(untilCond);
					if (cond)
					{
						break;
					}
				}
			}

			_metrics.competedLoop(metric, step < 0);

			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression whileCond = null;
			AbstractVariable loopVar = null;
			AbstractExpression startExpr = null;
			AbstractExpression stopExpr = null;
			AbstractExpression byExpr = null;
			EagleGenerator.RelationalEnum relOp = EagleGenerator.RelationalEnum.LESS_EQUALS;

			// Pick up options in the DO command
			if (doLoop != null && doLoop.isPresent())
			{
				loopVar = generator.newVariable(doLoop.id.getValue());
				startExpr = transformer.transformExpression(generator, doLoop.fromExpr);
				stopExpr = transformer.transformExpression(generator, doLoop.toExpr);
				if (doLoop.by != null && doLoop.by.isPresent())
				{
					byExpr = transformer.transformExpression(generator, doLoop.by.byExpr);
					if (doLoop.by.byExpr.getWhich() is PLI_Number)
					{
						PLI_Number number = (PLI_Number) doLoop.by.byExpr.getWhich();
						if (number.getValue().StartsWith("-"))
						{
							// What if it is a variable that happens to be negative? Yikes!
							relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
						}
					}
				}
			}
			if (doWhile != null && doWhile.isPresent())
			{
				whileCond = transformer.transformExpression(generator, doWhile.condition);
			}
			if (doUntil != null && doUntil.isPresent())
			{
				AbstractExpression untilCond = transformer.transformExpression(generator, doUntil.condition);
				whileCond = generator.newLogicalNotExpression(untilCond, doUntil);
			}
			if (FOREVER != null && FOREVER.isPresent())
			{
				whileCond = generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.TRUE, FOREVER);
			}

			// Body is the same for all types of 'DO'
			List<AbstractStatement> newStmts = new List<AbstractStatement>();
			foreach (PLI_StatementOrComment stmtComm in statements._elements)
			{
				AbstractToken which = stmtComm.getWhich();
				if (which is PLI_Statement)
				{
					PLI_Statement stmt = (PLI_Statement) which;
					AbstractStatement newStmt = transformer.transformStatement1(generator, stmt.getWhich());
					newStmts.Add(newStmt);
				}
			}

			// And now generate the output code
			if (loopVar != null)
			{
				if (whileCond != null)
				{
					AbstractStatement newIfStmt = generator.newIfStatement(whileCond, newStmts, null, this);
					return generator.newForRangeStatement1(loopVar, EagleGenerator.TypeEnum.VOID, startExpr, relOp, stopExpr, byExpr, newIfStmt, this);
				}
				return generator.newForRangeStatement(loopVar, EagleGenerator.TypeEnum.VOID, startExpr, relOp, stopExpr, byExpr, newStmts, this);
			}
			else if (whileCond != null)
			{
				return generator.newWhileStatement(whileCond, newStmts, DO);
			}
			else
			{
				// Simple DO / END block
				return generator.newBlockStatement(newStmts, DO);
			}
		}
	}

}
