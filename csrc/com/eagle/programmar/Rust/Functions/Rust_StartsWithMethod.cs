// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 22, 2024

namespace com.eagle.programmar.Rust.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_SubscriptExpression = com.eagle.programmar.Rust.Expressions.Rust_SubscriptExpression;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_StartsWithMethod : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Rust_Expression left = new com.eagle.programmar.Rust.Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Rust_Keyword STARTSWITH = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("starts_with");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE Rust_Expression arg;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			string text = interpreter.getStrValue(left);
			string patt = interpreter.getStrValue(arg);
			interpreter.pushBool(text.StartsWith(patt, StringComparison.Ordinal));
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression theExpr = transformer.transformExpression(generator, left);
			AbstractExpression thePattern = transformer.transformExpression(generator, arg);
			AbstractExpression theSC = null;
			return generator.newStartsWithFunction(theExpr, thePattern, theSC, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, this);
		}

		public static Rust_Expression generateStartsWith(Rust_Expression expr, Rust_Expression patt, Rust_Expression sc, EagleGenerator.SubstringSCEnum whichSC, AbstractToken source)
		{
			Rust_StartsWithMethod startsExpr = new Rust_StartsWithMethod();
			startsExpr.left = expr;
			startsExpr.dot = new PunctuationPeriod();
			startsExpr.leftParen = new PunctuationLeftParen();
			startsExpr.arg = patt;
			if (sc != null)
			{
				// Rust does not support str.StartsWith("patt",sc)
				// Have to use Substring instead
				startsExpr.left = Rust_SubscriptExpression.generateSubscriptExpression(expr, sc, whichSC, EagleGenerator.SubstringECEnum.GIVEN_NEITHER, null, false, source);
			}
			startsExpr.rightParen = new PunctuationRightParen();

			startsExpr.setTransformationSource(source);
			return Rust_Generator.wrapExpression(startsExpr);
		}
	}

}
