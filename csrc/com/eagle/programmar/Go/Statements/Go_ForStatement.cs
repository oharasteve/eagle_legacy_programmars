// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

namespace com.eagle.programmar.Go.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Go_Expression = com.eagle.programmar.Go.Go_Expression;
	using Go_Statement = com.eagle.programmar.Go.Go_Statement;
	using Go_Variable = com.eagle.programmar.Go.Go_Variable;
	using Go_PostIncrementExpression = com.eagle.programmar.Go.Expressions.Go_PostIncrementExpression;
	using Go_PreIncrementExpression = com.eagle.programmar.Go.Expressions.Go_PreIncrementExpression;
	using Go_RelationalExpression = com.eagle.programmar.Go.Expressions.Go_RelationalExpression;
	using Go_Identifier_Reference = com.eagle.programmar.Go.Symbols.Go_Identifier_Reference;
	using Go_Keyword = com.eagle.programmar.Go.Terminals.Go_Keyword;
	using Go_Punctuation = com.eagle.programmar.Go.Terminals.Go_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Go_ForStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#For_statements") com.eagle.programmar.Go.Terminals.Go_Keyword FOR = new com.eagle.programmar.Go.Terminals.Go_Keyword("for");
		public @DOC("#For_statements") Go_Keyword FOR = new Go_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Go.Symbols.Go_Identifier_Reference var;
		public Go_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Go.Terminals.Go_Punctuation colonEquals = new com.eagle.programmar.Go.Terminals.Go_Punctuation(":=");
		public Go_Punctuation colonEquals = new Go_Punctuation(":=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Go_ForWhat forWhat;
		public Go_ForWhat forWhat;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public static class Go_ForWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_ForLoop XXforLoop;
			public Go_ForLoop XXforLoop;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_ForRange XXforRange;
			public Go_ForRange XXforRange;
		}

		public static class Go_ForLoop extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Go.Go_Expression initValue;
			public Go_Expression initValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationSemicolon semiColon1;
			public PunctuationSemicolon semiColon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Go.Go_Expression condition;
			public Go_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semiColon2;
			public PunctuationSemicolon semiColon2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Go.Go_Expression increment;
			public Go_Expression increment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Go.Go_Statement statement;
			public Go_Statement statement;
		}

		public static class Go_ForRange extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Go.Terminals.Go_Keyword RANGE = new com.eagle.programmar.Go.Terminals.Go_Keyword("range");
			public Go_Keyword RANGE = new Go_Keyword("range");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Go.Go_Variable variable;
			public Go_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Go.Go_Statement statement;
			public Go_Statement statement;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (forWhat.getWhich() is Go_ForLoop)
			{
				Go_ForLoop forLoop = (Go_ForLoop) forWhat.getWhich();

				if (_metrics == null)
				{
					_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
				}
				ForLoopMetric metric = new ForLoopMetric();

				int current = interpreter.getIntValue(forLoop.initValue);

				string loopVar = var.getValue();
				interpreter.setSymbol(this, loopVar, new EagleInteger(current));

				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				while (true)
				{
					bool cond = interpreter.getBoolValue(forLoop.condition);
					if (!cond)
					{
						break;
					}

					metric.iterate();

					result = interpreter.tryToInterpret(forLoop.statement);
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

					interpreter.tryToInterpret(forLoop.increment);
				}

				// Have to guess to see if it was backwards
				bool backwards = guessDirection(forLoop.condition, forLoop.increment);

				_metrics.competedLoop(metric, backwards);
				return result;
			}

			throw new Exception("Cannot handle this type of for loop (yet): " + forWhat);
		}

		private static bool guessDirection(Go_Expression testExpr, Go_Expression incrExpr)
		{
			AbstractToken which1 = incrExpr.getWhich();
			if (which1 is Go_PostIncrementExpression)
			{
				Go_PostIncrementExpression post = (Go_PostIncrementExpression) which1;
				return post.@operator.getValue().Equals("--");
			}
			if (which1 is Go_PreIncrementExpression)
			{
				Go_PreIncrementExpression pre = (Go_PreIncrementExpression) which1;
				return pre.@operator.getValue().Equals("--");
			}

			AbstractToken which2 = testExpr.getWhich();
			if (which2 is Go_RelationalExpression)
			{
				Go_RelationalExpression rel = (Go_RelationalExpression) which2;
				string oper = rel.@operator.getValue();
				if (oper.Equals(">") || oper.Equals(">="))
				{
					return true;
				}
			}

			return false; // Just don't know :(
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (forWhat.getWhich() is Go_ForLoop)
			{
				Go_ForLoop forLoop = (Go_ForLoop) forWhat.getWhich();
				AbstractExpression initExpr = transformer.transformExpression(generator, forLoop.initValue);
				AbstractExpression asgExpr = generator.newAssignmentExpression(var.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.EQUALS, initExpr, forLoop.initValue);
				AbstractExpression termCond = transformer.transformExpression(generator, forLoop.condition);
				AbstractExpression incrExpr = transformer.transformExpression(generator, forLoop.increment);

				List<AbstractStatement> whileTrue = new List<AbstractStatement>();
				List<AbstractStatement> stmts = transformer.transformStatement(generator, forLoop.statement.getWhich());
				if (stmts != null)
				{
					foreach (AbstractStatement stmt in stmts)
					{
						whileTrue.Add(stmt);
					}
				}

				return generator.newForLoopStatement(asgExpr, termCond, incrExpr, whileTrue, this);
			}

			throw new Exception("Cannot handle this type of for loop (yet): " + forWhat);
		}
	}
}
