// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

namespace com.eagle.programmar.CSharp.Methods
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_AdditiveExpression = com.eagle.programmar.CSharp.Expressions.CSharp_AdditiveExpression;
	using CSharp_ParenthesizedExpression = com.eagle.programmar.CSharp.Expressions.CSharp_ParenthesizedExpression;
	using CSharp_MathFunction = com.eagle.programmar.CSharp.Functions.CSharp_MathFunction;
	using CSharp_MathMinMaxFunc = com.eagle.programmar.CSharp.Functions.CSharp_MathMinMaxFunc;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using CSharp_Number = com.eagle.programmar.CSharp.Terminals.CSharp_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class CSharp_SubstringMethod : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.CSharp_Expression left = new com.eagle.programmar.CSharp.CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE CSharp_Keyword SUBSTRING = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("Substring");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE CSharp_Expression scExpr;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT @NOSPACE PunctuationComma comma;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT CSharp_Expression ncExpr;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			string leftStr = interpreter.getStrValue(left);
			int sc = interpreter.getIntValue(scExpr);
			if (ncExpr != null && ncExpr.isPresent())
			{
				int nc = interpreter.getIntValue(ncExpr);
				interpreter.pushStr(leftStr.Substring(sc, nc));
			}
			else
			{
				interpreter.pushStr(leftStr.Substring(sc));
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression theExpr = transformer.transformExpression(generator, left);
			AbstractExpression sc = transformer.transformExpression(generator, scExpr);
			AbstractExpression nc = null;
			EagleGenerator.SubstringECEnum given = EagleGenerator.SubstringECEnum.GIVEN_NEITHER;
			if (ncExpr != null && ncExpr.isPresent())
			{
				nc = transformer.transformExpression(generator, ncExpr);
				given = EagleGenerator.SubstringECEnum.GIVEN_NC;
			}
			return generator.newSubstringFunction(theExpr, sc, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, given, nc, true, this);
		}

		public static CSharp_Expression generateExpression(AbstractExpression theExpr, AbstractExpression sc, EagleGenerator.SubstringSCEnum whichSC, EagleGenerator.SubstringECEnum whichEC, AbstractExpression ecOrnc, bool ncMightBeTooBig, AbstractToken source)
		{
			CSharp_SubstringMethod expr = new CSharp_SubstringMethod();
			expr.dot = new PunctuationPeriod();
			expr.left = (CSharp_Expression) theExpr;
			expr.leftParen = new PunctuationLeftParen();
			expr.rightParen = new PunctuationRightParen();

			switch (whichSC)
			{
			case FIRST_CHAR_IS_ZERO:
				expr.scExpr = (CSharp_Expression) sc;
				break;
			case FIRST_CHAR_IS_ONE:
				CSharp_Expression one = CSharp_Number.generateNumberExpression("1", source);
				Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.INTEGER, EagleGenerator.TypeEnum.INTEGER);
				CSharp_Expression scMinusOne = CSharp_AdditiveExpression.generateAdditive(types, (CSharp_Expression) sc, EagleGenerator.AdditiveEnum.MINUS, one, source);
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
					CSharp_Expression one = CSharp_Number.generateNumberExpression("1", source);
					Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.INTEGER, EagleGenerator.TypeEnum.INTEGER);
					CSharp_Expression ecPlusOne = CSharp_AdditiveExpression.generateAdditive(types, (CSharp_Expression) ecOrnc, EagleGenerator.AdditiveEnum.PLUS, one, source);
					CSharp_Expression scParensExpr = CSharp_ParenthesizedExpression.generateParentheses((CSharp_Expression) sc, null);
					CSharp_Expression ecMinusSc = CSharp_AdditiveExpression.generateAdditive(types, ecPlusOne, EagleGenerator.AdditiveEnum.MINUS, scParensExpr, source);
					CSharp_Expression ncExpr = ecMinusSc;
					expr.ncExpr = ncExpr;
					expr.ncExpr.setPresent(true);
				}
				break;
			case GIVEN_EC_PLUS_ONE:
				if (ecOrnc != null)
				{
					expr.comma = new PunctuationComma();
					expr.comma.setPresent(true);
					Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.INTEGER, EagleGenerator.TypeEnum.INTEGER);
					CSharp_Expression scParensExpr = CSharp_ParenthesizedExpression.generateParentheses((CSharp_Expression) sc, null);
					CSharp_Expression ecMinusSc = CSharp_AdditiveExpression.generateAdditive(types, (CSharp_Expression) ecOrnc, EagleGenerator.AdditiveEnum.MINUS, scParensExpr, source);
					CSharp_Expression ncExpr = ecMinusSc;
					expr.ncExpr = ncExpr;
					expr.ncExpr.setPresent(true);
				}
				break;
			case GIVEN_NC:
				if (ecOrnc != null)
				{
					expr.comma = new PunctuationComma();
					expr.comma.setPresent(true);
					expr.ncExpr = (CSharp_Expression) ecOrnc;
					expr.ncExpr.setPresent(true);
				}
				break;
			case GIVEN_NEITHER:
				expr.ncExpr = null;
				break;
			}

			// Need to handle ncMightBeTooBig. Can't let nc go past len(left) minus sc
			if (ncMightBeTooBig && expr.ncExpr != null)
			{
				CSharp_Expression lenExp = CSharp_LengthMethod.generateLength((CSharp_Expression) theExpr, source);
				CSharp_Expression parenExp = CSharp_ParenthesizedExpression.generateParentheses(expr.scExpr, source);
				CSharp_Expression subtrExp = CSharp_AdditiveExpression.generateAdditive(null, lenExp, EagleGenerator.AdditiveEnum.MINUS, parenExp, source);

				CSharp_MathMinMaxFunc minFn = new CSharp_MathMinMaxFunc();
				minFn.leftParen = new PunctuationLeftParen();
				minFn.expressions = new SeparatedList<CSharp_Expression, PunctuationComma>();
				minFn.expressions.addPrimaryElement(expr.ncExpr);
				minFn.expressions.addSecondaryElement(new PunctuationComma());
				minFn.expressions.addPrimaryElement(subtrExp);
				minFn.rightParen = new PunctuationRightParen();

				expr.ncExpr = CSharp_MathFunction.wrapMathFunction(minFn, source);
				expr.ncExpr.setPresent(true);
			}

			expr.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(expr);
		}
	}

}
