// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

namespace com.eagle.programmar.C.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_Statement = com.eagle.programmar.C.C_Statement;
	using C_Syntax = com.eagle.programmar.C.C_Syntax;
	using C_Type = com.eagle.programmar.C.C_Type;
	using C_Variable = com.eagle.programmar.C.C_Variable;
	using C_PostIncrementVariable = com.eagle.programmar.C.Expressions.C_PostIncrementVariable;
	using C_PreIncrementExpression = com.eagle.programmar.C.Expressions.C_PreIncrementExpression;
	using C_RelationalExpression = com.eagle.programmar.C.Expressions.C_RelationalExpression;
	using C_Variable_Definition = com.eagle.programmar.C.Symbols.C_Variable_Definition;
	using C_Comment = com.eagle.programmar.C.Terminals.C_Comment;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class C_ForStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleScope.EagleScopeInterface, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#The-for-Statement") com.eagle.programmar.C.Terminals.C_Keyword FOR = new com.eagle.programmar.C.Terminals.C_Keyword("for");
		public @DOC("#The-for-Statement") C_Keyword FOR = new C_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) C_ForLoopBody body;
		public C_ForLoopBody body;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.C_Statement action;
		public C_Statement action;

		public static class C_ForLoopBody extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_ForLoopStatement XXloopStatement;
			public C_ForLoopStatement XXloopStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_ForCollectionStatement XXcollectionStatement;
			public C_ForCollectionStatement XXcollectionStatement;
		}

		public static class C_ForLoopStatement extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<C_ForLoopVariable, com.eagle.tokens.punctuation.PunctuationComma> loopVar;
			public @OPT SeparatedList<C_ForLoopVariable, PunctuationComma> loopVar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT C_Comment comment1;
			public @OPT C_Comment comment1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon1;
			public PunctuationSemicolon semicolon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT C_Expression terminateCondition;
			public @OPT C_Expression terminateCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT C_Comment comment2;
			public @OPT C_Comment comment2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon2;
			public PunctuationSemicolon semicolon2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT C_Expression increment;
			public @OPT C_Expression increment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT TokenList<C_MoreLoopIncrements> moreLoopIncrements;
			public @OPT TokenList<C_MoreLoopIncrements> moreLoopIncrements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT C_Comment comment3;
			public @OPT C_Comment comment3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) @OPT C_Comment comment4;
			public @OPT C_Comment comment4;

			public static class C_ForLoopVariable extends TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST C_ForWithType XXforWithType;
				public C_ForWithType XXforWithType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_ForWithoutType XXforWithoutType;
				public C_ForWithoutType XXforWithoutType;
			}

			public static class C_MoreLoopIncrements extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
				public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) C_ForLoopVariable forVar;
				public C_ForLoopVariable forVar;
			}
		}

		public static class C_ForCollectionStatement extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_Keyword CONST = new com.eagle.programmar.C.Terminals.C_Keyword("const");
			public @OPT C_Keyword CONST = new C_Keyword("const");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.C_Type varType;
			public C_Type varType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.C.C_Variable forVar;
			public C_Variable forVar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.C.C_Expression collection;
			public C_Expression collection;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static class C_ForWithType extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.C_Type varType;
			public C_Type varType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Symbols.C_Variable_Definition variable;
			public C_Variable_Definition variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT C_ForTypeInit equalsInit;
			public @OPT C_ForTypeInit equalsInit;

			public static class C_ForTypeInit extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.C_Expression initialExpr;
				public C_Expression initialExpr;
			}
		}

		public static class C_ForWithoutType extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Symbols.C_Variable_Definition variable;
			public C_Variable_Definition variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.C_Expression initialExpr;
			public C_Expression initialExpr;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.C.C_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, C_Syntax.IS_CASE_SENSITIVE);

		public EagleScope Scope
		{
			return _scope;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (body.getWhich() is C_ForLoopStatement)
			{
				C_ForLoopStatement loop = (C_ForLoopStatement) body.getWhich();

				AbstractToken which = loop.loopVar.first().getWhich();
				if (which is C_ForWithType)
				{
					C_ForWithType whatforWith = (C_ForWithType) which;
					EagleValue initial = interpreter.getEagleValue(whatforWith.equalsInit.initialExpr);
					interpreter.setSymbol(whatforWith.variable, whatforWith.variable.getValue(), initial);
				}
				else if (which is C_ForWithoutType)
				{
					C_ForWithoutType noType = (C_ForWithoutType) which;
					EagleValue initial = interpreter.getEagleValue(noType.initialExpr);
					interpreter.setSymbol(noType.variable, noType.variable.getValue(), initial);
				}
				else
				{
					throw new Exception("Cannot handle " + which);
				}

				if (_metrics == null)
				{
					_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
				}
				ForLoopMetric metric = new ForLoopMetric();

				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				while (true)
				{
					bool keepGoing = interpreter.getBoolValue(loop.terminateCondition);
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

					interpreter.tryToInterpret(loop.increment);
				}

				// Have to guess to see if it was backwards
				bool backwards = guessDirection(loop.terminateCondition, loop.increment);

				_metrics.competedLoop(metric, backwards);
				return result;
			}

			throw new Exception("Unexpected for loop construct: " + body.getWhich());
		}

		private static bool guessDirection(C_Expression testExpr, C_Expression incrExpr)
		{
			AbstractToken which1 = incrExpr.getWhich();
			if (which1 is C_PostIncrementVariable)
			{
				C_PostIncrementVariable post = (C_PostIncrementVariable) which1;
				return post.@operator.getValue().Equals("--");
			}
			if (which1 is C_PreIncrementExpression)
			{
				C_PreIncrementExpression pre = (C_PreIncrementExpression) which1;
				return pre.@operator.getValue().Equals("--");
			}

			AbstractToken which2 = testExpr.getWhich();
			if (which2 is C_RelationalExpression)
			{
				C_RelationalExpression rel = (C_RelationalExpression) which2;
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
			if (body.getWhich() is C_ForLoopStatement)
			{
				C_ForLoopStatement loop = (C_ForLoopStatement) body.getWhich();
				string varName = null;
				C_Expression forInit = null;
				AbstractToken which = loop.loopVar.first().getWhich();
				if (which is C_ForWithType)
				{
					C_ForWithType withType = (C_ForWithType) which;
					varName = withType.variable.getValue();
					forInit = withType.equalsInit.initialExpr;
				}
				else if (which is C_ForWithoutType)
				{
					C_ForWithoutType noType = (C_ForWithoutType) which;
					varName = noType.variable.getValue();
					forInit = noType.initialExpr;
				}

				if (forInit != null)
				{
					AbstractExpression fromExpr = transformer.transformExpression(generator, forInit);
					AbstractExpression asgExpr = generator.newAssignmentExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.EQUALS, fromExpr, null);

					AbstractExpression termExpr = transformer.transformExpression(generator, loop.terminateCondition);
					AbstractExpression delta = transformer.transformExpression(generator, loop.increment);
					AbstractStatement newAction = transformer.transformStatement1(generator, this.action);
					return generator.newForLoopStatement1(asgExpr, termExpr, delta, newAction, this);
				}
			}

			throw new Exception("Unable to handle for loop: " + this);
		}
	}

}
