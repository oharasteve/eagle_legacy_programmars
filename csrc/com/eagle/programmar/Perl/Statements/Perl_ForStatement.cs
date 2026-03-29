// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

namespace com.eagle.programmar.Perl.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_Statement = com.eagle.programmar.Perl.Perl_Statement;
	using Perl_Variable = com.eagle.programmar.Perl.Perl_Variable;
	using Perl_UserVariable = com.eagle.programmar.Perl.Perl_Variable.Perl_UserVariable;
	using Perl_ParenthesizedExpression = com.eagle.programmar.Perl.Expressions.Perl_ParenthesizedExpression;
	using Perl_PostIncrementExpression = com.eagle.programmar.Perl.Expressions.Perl_PostIncrementExpression;
	using Perl_PreIncrementExpression = com.eagle.programmar.Perl.Expressions.Perl_PreIncrementExpression;
	using Perl_RelationalExpression = com.eagle.programmar.Perl.Expressions.Perl_RelationalExpression;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Perl_ForStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("control-structures.for.php") com.eagle.programmar.Perl.Terminals.Perl_Keyword FOR = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("for");
		public @DOC("control-structures.for.php") Perl_Keyword FOR = new Perl_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Perl_ForWhat forWhat;
		public Perl_ForWhat forWhat;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Perl_Statement action;
		public Perl_Statement action;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public static class Perl_ForWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_ForVarInSet XXvarInSet;
			public Perl_ForVarInSet XXvarInSet;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_ForLikeC XXlikeC;
			public Perl_ForLikeC XXlikeC;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST Perl_ParenthesizedExpression XXexpr;
			public Perl_ParenthesizedExpression XXexpr;
		}

		public static class Perl_ForVarInSet extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword MY = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("my");
			public Perl_Keyword MY = new Perl_Keyword("my");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Perl_Variable var;
			public Perl_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Perl.Perl_Expression initExpr;
			public Perl_Expression initExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static class Perl_ForLikeC extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Perl_Variable variable;
			public Perl_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Perl.Perl_Expression initExpr;
			public Perl_Expression initExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon1;
			public PunctuationSemicolon semicolon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Perl_Expression testExpr;
			public @OPT Perl_Expression testExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon2;
			public PunctuationSemicolon semicolon2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT Perl_Expression incrExpr;
			public @OPT Perl_Expression incrExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (forWhat.getWhich() is Perl_ForLikeC)
			{
				Perl_ForLikeC forLikeC = (Perl_ForLikeC) forWhat.getWhich();

				AbstractToken which = forLikeC.variable.getWhich();
				if (!(which is Perl_Variable.Perl_UserVariable))
				{
					throw new Exception("Must be a simple variable");
				}
				Perl_Variable.Perl_UserVariable userVar = (Perl_Variable.Perl_UserVariable) which;
				EagleValue initial = interpreter.getEagleValue(forLikeC.initExpr);
				interpreter.setSymbol(forLikeC.variable, userVar.id.getValue(), initial);

				if (_metrics == null)
				{
					_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
				}
				ForLoopMetric metric = new ForLoopMetric();

				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				while (true)
				{
					bool keepGoing = interpreter.getBoolValue(forLikeC.testExpr);
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

					interpreter.tryToInterpret(forLikeC.incrExpr);
				}

				// Have to guess to see if it was backwards
				bool backwards = guessDirection(forLikeC.testExpr, forLikeC.incrExpr);

				_metrics.competedLoop(metric, backwards);
				return result;
			}

			throw new Exception("Unexpected for loop construct: " + forWhat.getWhich());
		}

		private static bool guessDirection(Perl_Expression testExpr, Perl_Expression incrExpr)
		{
			AbstractToken which1 = incrExpr.getWhich();
			if (which1 is Perl_PostIncrementExpression)
			{
				Perl_PostIncrementExpression post = (Perl_PostIncrementExpression) which1;
				return post.@operator.getValue().Equals("--");
			}
			if (which1 is Perl_PreIncrementExpression)
			{
				Perl_PreIncrementExpression pre = (Perl_PreIncrementExpression) which1;
				return pre.@operator.getValue().Equals("--");
			}

			AbstractToken which2 = testExpr.getWhich();
			if (which2 is Perl_RelationalExpression)
			{
				Perl_RelationalExpression rel = (Perl_RelationalExpression) which2;
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
			if (!(forWhat.getWhich() is Perl_ForLikeC))
			{
				throw new Exception("Can only handle regular for loops");
			}
			Perl_ForLikeC forLikeC = (Perl_ForLikeC) forWhat.getWhich();

			AbstractToken which = forLikeC.variable.getWhich();
			if (!(which is Perl_Variable.Perl_UserVariable))
			{
				throw new Exception("Must be a simple variable");
			}
			Perl_Variable.Perl_UserVariable userVar = (Perl_Variable.Perl_UserVariable) which;
			string newName = Perl_Variable.repairName(userVar.id.getValue());

			Perl_Expression forInit = forLikeC.initExpr;
			AbstractExpression fromExpr = transformer.transformExpression(generator, forInit);
			AbstractExpression asgExpr = generator.newAssignmentExpression(newName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.EQUALS, fromExpr, null);

			AbstractExpression termExpr = transformer.transformExpression(generator, forLikeC.testExpr);
			AbstractExpression delta = transformer.transformExpression(generator, forLikeC.incrExpr);
			AbstractStatement newAction = transformer.transformStatement1(generator, this.action);
			return generator.newForLoopStatement1(asgExpr, termExpr, delta, newAction, this);
		}
	}

}
