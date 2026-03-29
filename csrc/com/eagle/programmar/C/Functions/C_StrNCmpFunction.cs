// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

namespace com.eagle.programmar.C.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_PunctuationChoice = com.eagle.programmar.C.Terminals.C_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class C_StrNCmpFunction : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword STRNCMP = new com.eagle.programmar.C.Terminals.C_Keyword("strncmp");
		public C_Keyword STRNCMP = new C_Keyword("strncmp");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.C_Expression str1;
		public C_Expression str1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationComma comma1;
		public PunctuationComma comma1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.C.C_Expression str2;
		public C_Expression str2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationComma comma2;
		public PunctuationComma comma2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.C.C_Expression ncExpr;
		public C_Expression ncExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.C.Terminals.C_PunctuationChoice operator = new com.eagle.programmar.C.Terminals.C_PunctuationChoice("==", "!=", "<", ">=");
		public C_PunctuationChoice @operator = new C_PunctuationChoice("==", "!=", "<", ">=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.C.Terminals.C_Keyword ZERO = new com.eagle.programmar.C.Terminals.C_Keyword("0");
		public C_Keyword ZERO = new C_Keyword("0");

		public override void interpret(EagleInterpreter interpreter)
		{
			string left = interpreter.getStrValue(str1);
			string right = interpreter.getStrValue(str2);
			int nc = interpreter.getIntValue(ncExpr);
			if (left.Length > nc)
			{
				left = left.Substring(0, nc);
			}
			if (right.Length > nc)
			{
				right = right.Substring(0, nc);
			}
			int compare = string.CompareOrdinal(left, right);

			switch (@operator.getValue())
			{
			case "==", ">=":
				interpreter.pushBool(compare == 0);
				return;
			case "!=", "<":
				interpreter.pushBool(compare != 0);
				return;
			}

			throw new Exception("Unexpected operator: " + @operator.getValue());
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.STRING, EagleGenerator.TypeEnum.STRING);
			AbstractExpression newNc = transformer.transformExpression(generator, ncExpr);
			AbstractExpression zero = generator.newNumberExpression("0", null);
			AbstractExpression newStr1 = transformer.transformExpression(generator, str1);
			AbstractExpression newStr2 = transformer.transformExpression(generator, str2);
			AbstractExpression substr1 = generator.newSubstringFunction(newStr1, zero, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, EagleGenerator.SubstringECEnum.GIVEN_NC, newNc, true, str1);
			AbstractExpression substr2 = generator.newSubstringFunction(newStr2, zero, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, EagleGenerator.SubstringECEnum.GIVEN_NC, newNc, true, str2);

			switch (@operator.getValue())
			{
			case "==", ">=":
				return generator.newRelationalExpression(types, substr1, EagleGenerator.RelationalEnum.EQUALS, substr2, this);
			case "!=", "<":
				return generator.newRelationalExpression(types, substr1, EagleGenerator.RelationalEnum.NOT_EQUALS, substr2, this);
			}

			throw new Exception("Unexpected operator: " + @operator.getValue());
		}
	}

}
