// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.Javascript.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Javascript_More_Variables = com.eagle.programmar.Javascript.Javascript_Data.Javascript_More_Variables;
	using Javascript_Element = com.eagle.programmar.Javascript.Javascript_Element;
	using Javascript_Expression = com.eagle.programmar.Javascript.Javascript_Expression;
	using Javascript_Type = com.eagle.programmar.Javascript.Javascript_Type;
	using Javascript_Variable = com.eagle.programmar.Javascript.Javascript_Variable;
	using Javascript_PostIncrementExpression = com.eagle.programmar.Javascript.Expressions.Javascript_PostIncrementExpression;
	using Javascript_PreIncrementExpression = com.eagle.programmar.Javascript.Expressions.Javascript_PreIncrementExpression;
	using Javascript_RelationalExpression = com.eagle.programmar.Javascript.Expressions.Javascript_RelationalExpression;
	using Javascript_Identifier_Reference = com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
	using Javascript_Comment = com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
	using Javascript_Keyword = com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
	using Javascript_Punctuation = com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Javascript_ForStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("js_loop_for.asp") com.eagle.programmar.Javascript.Terminals.Javascript_Keyword FOR = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("for");
		public @DOC("js_loop_for.asp") Javascript_Keyword FOR = new Javascript_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Javascript_ForLoopStatement forLoop;
		public Javascript_ForLoopStatement forLoop;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<com.eagle.programmar.Javascript.Terminals.Javascript_Comment> comments;
		public @OPT TokenList<Javascript_Comment> comments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Javascript.Javascript_Element action;
		public Javascript_Element action;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public static class Javascript_ForLoopStatement extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Javascript_ForLoopVariable loopVar;
			public @OPT Javascript_ForLoopVariable loopVar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Javascript_Punctuation equals = new com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation("=");
			public @OPT Javascript_Punctuation equals = new Javascript_Punctuation("=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Javascript_Expression initialize;
			public @OPT Javascript_Expression initialize;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Javascript.Javascript_Data.Javascript_More_Variables> moreVariables;
			public @OPT TokenList<Javascript_More_Variables> moreVariables;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon1;
			public PunctuationSemicolon semicolon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Javascript_Expression terminateCondition;
			public @OPT Javascript_Expression terminateCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon2;
			public PunctuationSemicolon semicolon2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT Javascript_Expression increment;
			public @OPT Javascript_Expression increment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT PunctuationComma comma;
			public @OPT PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT Javascript_Expression extraIncrement;
			public @OPT Javascript_Expression extraIncrement;

			public static class Javascript_ForLoopVariable extends TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST Javascript_ForLoopVariableWithType XXwithType;
				public Javascript_ForLoopVariableWithType XXwithType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_ForLoopVariableNoType XXnoType;
				public Javascript_ForLoopVariableNoType XXnoType;
			}
		}

		public static class Javascript_ForLoopVariableWithType extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Javascript_Type varType;
			public Javascript_Type varType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Javascript.Javascript_Variable forVar;
			public Javascript_Variable forVar;
		}

		public static class Javascript_ForLoopVariableNoType extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Javascript_Variable forVar;
			public Javascript_Variable forVar;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			AbstractToken which = forLoop.loopVar.getWhich();
			Javascript_Variable forVar;
			if (which is Javascript_ForLoopVariableWithType)
			{
				Javascript_ForLoopVariableWithType withType = (Javascript_ForLoopVariableWithType) which;
				forVar = withType.forVar;
			}
			else
			{
				Javascript_ForLoopVariableNoType noType = (Javascript_ForLoopVariableNoType) which;
				forVar = noType.forVar;
			}

			if (forVar.firstId.getWhich() is Javascript_Identifier_Reference)
			{
				Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) forVar.firstId.getWhich();
				EagleValue init = interpreter.getEagleValue(forLoop.initialize);
				interpreter.setSymbol(this, id.getValue(), init);
			}

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				bool keepGoing = interpreter.getBoolValue(forLoop.terminateCondition);
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

				interpreter.tryToInterpret(forLoop.increment);
			}

			// Have to guess to see if it was backwards
			bool backwards = guessDirection(forLoop.terminateCondition, forLoop.increment);

			_metrics.competedLoop(metric, backwards);
			return result;
		}

		private static bool guessDirection(Javascript_Expression testExpr, Javascript_Expression incrExpr)
		{
			AbstractToken which1 = incrExpr.getWhich();
			if (which1 is Javascript_PostIncrementExpression)
			{
				Javascript_PostIncrementExpression post = (Javascript_PostIncrementExpression) which1;
				return post.@operator.getValue().Equals("--");
			}
			if (which1 is Javascript_PreIncrementExpression)
			{
				Javascript_PreIncrementExpression pre = (Javascript_PreIncrementExpression) which1;
				return pre.@operator.getValue().Equals("--");
			}

			AbstractToken which2 = testExpr.getWhich();
			if (which2 is Javascript_RelationalExpression)
			{
				Javascript_RelationalExpression rel = (Javascript_RelationalExpression) which2;
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
			AbstractToken which1 = forLoop.loopVar.getWhich();
			Javascript_Variable forVar;
			if (which1 is Javascript_ForLoopVariableWithType)
			{
				Javascript_ForLoopVariableWithType withType = (Javascript_ForLoopVariableWithType) which1;
				forVar = withType.forVar;
			}
			else
			{
				Javascript_ForLoopVariableNoType noType = (Javascript_ForLoopVariableNoType) which1;
				forVar = noType.forVar;
			}

			AbstractToken whichName = forVar.firstId.getWhich();
			if (!(whichName is Javascript_Identifier_Reference))
			{
				throw new Exception("Javascript FOR must use a variable");
			}
			Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) whichName;
			string varName = id.getValue();

			AbstractExpression fromExpr = transformer.transformExpression(generator, forLoop.initialize);
			AbstractExpression asgExpr = generator.newAssignmentExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.EQUALS, fromExpr, null);

			AbstractExpression termExpr = transformer.transformExpression(generator, forLoop.terminateCondition);
			AbstractExpression delta = transformer.transformExpression(generator, forLoop.increment);
			AbstractStatement newAction = transformer.transformStatement1(generator, this.action.statement);
			return generator.newForLoopStatement1(asgExpr, termExpr, delta, newAction, this);
		}
	}
}
