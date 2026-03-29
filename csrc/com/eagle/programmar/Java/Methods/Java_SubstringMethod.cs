// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

namespace com.eagle.programmar.Java.Methods
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_AdditiveExpression = com.eagle.programmar.Java.Expressions.Java_AdditiveExpression;
	using Java_MathFunction = com.eagle.programmar.Java.Functions.Java_MathFunction;
	using Java_MathMinMaxFunc = com.eagle.programmar.Java.Functions.Java_MathMinMaxFunc;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using Java_Number = com.eagle.programmar.Java.Terminals.Java_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Java_SubstringMethod : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Expression left = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Java_Keyword SUBSTRING = new com.eagle.programmar.Java.Terminals.Java_Keyword("substring");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE Java_Expression scExpr;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT @NOSPACE PunctuationComma comma;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Java_Expression ecExpr;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			string leftStr = interpreter.getStrValue(left);
			int sc = interpreter.getIntValue(scExpr);
			if (ecExpr != null && ecExpr.isPresent())
			{
				int ec = interpreter.getIntValue(ecExpr);
				interpreter.pushStr(leftStr.Substring(sc, ec - sc));
			}
			else
			{
				interpreter.pushStr(leftStr.Substring(sc));
			}
		}

		public static Java_Expression generateExpression(AbstractExpression theExpr, AbstractExpression sc, SubstringSCEnum whichSC, SubstringECEnum whichEC, AbstractExpression ecOrnc, bool ncMightBeTooBig, AbstractToken source)
		{
			Java_SubstringMethod expr = new Java_SubstringMethod();
			expr.dot = new PunctuationPeriod();
			expr.left = (Java_Expression) theExpr;
			expr.leftParen = new PunctuationLeftParen();
			expr.rightParen = new PunctuationRightParen();

			switch (whichSC)
			{
			case FIRST_CHAR_IS_ZERO:
				expr.scExpr = (Java_Expression) sc;
				break;
			case FIRST_CHAR_IS_ONE:
				Java_Expression one = Java_Number.generateNumberExpression("1", source);
				Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
				Java_Expression scMinusOne = Java_AdditiveExpression.generateAdditive(types, (Java_Expression) sc, AdditiveEnum.MINUS, one, source);
				expr.scExpr = scMinusOne;
				break;
			}

			switch (whichEC)
			{
			case GIVEN_EC:
				if (ecOrnc != null)
				{
					expr.comma = new PunctuationComma();
					expr.comma.setPresent(true);
					switch (whichSC)
					{
					case FIRST_CHAR_IS_ZERO:
						Java_Expression one = Java_Number.generateNumberExpression("1", source);
						Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
						Java_Expression ecPlusOne = Java_AdditiveExpression.generateAdditive(types, (Java_Expression) ecOrnc, AdditiveEnum.PLUS, one, source);
						expr.ecExpr = ecPlusOne;
						break;
					case FIRST_CHAR_IS_ONE:
						expr.ecExpr = (Java_Expression) ecOrnc;
						break;
					}
					expr.ecExpr.setPresent(true);
				}
				break;
			case GIVEN_EC_PLUS_ONE:
				if (ecOrnc != null)
				{
					expr.comma = new PunctuationComma();
					expr.comma.setPresent(true);
					expr.ecExpr = (Java_Expression) ecOrnc;
					expr.ecExpr.setPresent(true);
				}
				break;
			case GIVEN_NC:
				expr.comma = new PunctuationComma();
				expr.comma.setPresent(true);
				Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
				Java_Expression scPlusNc = Java_AdditiveExpression.generateAdditive(types, expr.scExpr, AdditiveEnum.PLUS, (Java_Expression) ecOrnc, source);
				expr.ecExpr = scPlusNc;
				expr.ecExpr.setPresent(true);
				break;
			case GIVEN_NEITHER:
				expr.ecExpr = null;
				break;
			}

			// Need to handle ncMightBeTooBig. Can't let ec go past len(left)
			if (ncMightBeTooBig && expr.ecExpr != null)
			{
				Java_MathMinMaxFunc minFn = new Java_MathMinMaxFunc();
				minFn.leftParen = new PunctuationLeftParen();
				minFn.expressions = new SeparatedList<Java_Expression, PunctuationComma>();
				minFn.expressions.addPrimaryElement(expr.ecExpr);
				minFn.expressions.addSecondaryElement(new PunctuationComma());
				minFn.rightParen = new PunctuationRightParen();

				Java_Expression len = Java_LengthMethod.generateLength((Java_Expression) theExpr, source);
				minFn.expressions.addPrimaryElement(len);

				expr.ecExpr = Java_MathFunction.wrapMathFunction(minFn, source);
				expr.ecExpr.setPresent(true);
			}

			expr.setTransformationSource(source);
			return Java_Generator.wrapExpression(expr);
		}
	}

}
