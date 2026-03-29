// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2015

namespace com.eagle.programmar.AWK.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using AWK_Action = com.eagle.programmar.AWK.AWK_Action;
	using AWK_StatementOrComment = com.eagle.programmar.AWK.AWK_Action.AWK_StatementOrComment;
	using AWK_Expression = com.eagle.programmar.AWK.AWK_Expression;
	using AWK_Statement = com.eagle.programmar.AWK.AWK_Statements.AWK_Statement;
	using AWK_Variable = com.eagle.programmar.AWK.AWK_Variable;
	using AWK_PostIncrementExpression = com.eagle.programmar.AWK.Expressions.AWK_PostIncrementExpression;
	using AWK_PreIncrementExpression = com.eagle.programmar.AWK.Expressions.AWK_PreIncrementExpression;
	using AWK_RelationalExpression = com.eagle.programmar.AWK.Expressions.AWK_RelationalExpression;
	using AWK_EndOfLine = com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
	using AWK_Keyword = com.eagle.programmar.AWK.Terminals.AWK_Keyword;
	using AWK_Punctuation = com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class AWK_ForStatement : TokenSequence, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#For-Statement") com.eagle.programmar.AWK.Terminals.AWK_Keyword FOR = new com.eagle.programmar.AWK.Terminals.AWK_Keyword("for");
		public @DOC("#For-Statement") AWK_Keyword FOR = new AWK_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.AWK.AWK_Variable loopVar;
		public AWK_Variable loopVar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.AWK.Terminals.AWK_Punctuation equals = new com.eagle.programmar.AWK.Terminals.AWK_Punctuation("=");
		public AWK_Punctuation equals = new AWK_Punctuation("=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.AWK.AWK_Expression initialize;
		public AWK_Expression initialize;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon1;
		public PunctuationSemicolon semicolon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.AWK.AWK_Expression test;
		public AWK_Expression test;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon2;
		public PunctuationSemicolon semicolon2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.AWK.AWK_Expression increment;
		public AWK_Expression increment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT AWK_EndOfLine eoln;
		public @OPT AWK_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) AWK_ForBlock block;
		public AWK_ForBlock block;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public static class AWK_ForBlock extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_Statement XXstmt;
			public AWK_Statement XXstmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_Action XXactions;
			public AWK_Action XXactions;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			EagleValue init = interpreter.getEagleValue(initialize);
			interpreter.setSymbol(this, loopVar.id.getValue(), init);

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				bool keepGoing = interpreter.getBoolValue(test);
				if (!keepGoing)
				{
					break;
				}

				metric.iterate();
				AWK_Action stmts = (AWK_Action) block.getWhich();

				foreach (AWK_Action.AWK_StatementOrComment stmt in stmts.statements._elements)
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

				interpreter.tryToInterpret(increment);
			}

			// Have to guess to see if it was backwards
			bool backwards = guessDirection(test, increment);

			_metrics.competedLoop(metric, backwards);
			return result;
		}

		private static bool guessDirection(AWK_Expression testExpr, AWK_Expression incrExpr)
		{
			AbstractToken which1 = incrExpr.getWhich();
			if (which1 is AWK_PostIncrementExpression)
			{
				AWK_PostIncrementExpression post = (AWK_PostIncrementExpression) which1;
				return post.@operator.getValue().Equals("--");
			}
			if (which1 is AWK_PreIncrementExpression)
			{
				AWK_PreIncrementExpression pre = (AWK_PreIncrementExpression) which1;
				return pre.@operator.getValue().Equals("--");
			}

			AbstractToken which2 = testExpr.getWhich();
			if (which2 is AWK_RelationalExpression)
			{
				AWK_RelationalExpression rel = (AWK_RelationalExpression) which2;
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
			string varName = loopVar.id.getValue();

			AbstractExpression fromExpr = transformer.transformExpression(generator, initialize);
			AbstractExpression asgExpr = generator.newAssignmentExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.EQUALS, fromExpr, null);

			AbstractExpression termExpr = transformer.transformExpression(generator, test);
			AbstractExpression delta = transformer.transformExpression(generator, increment);
			List<AbstractStatement> newActions = transformer.transformStatement(generator, block);
			return generator.newForLoopStatement(asgExpr, termExpr, delta, newActions, this);
		}
	}

}
