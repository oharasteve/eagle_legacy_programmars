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
	using C_KeywordChoice = com.eagle.programmar.C.Terminals.C_KeywordChoice;
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
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class C_StrCmpFunction : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_KeywordChoice STRCMP = new com.eagle.programmar.C.Terminals.C_KeywordChoice("strcmp", "stricmp", "strcasecmp");
		public C_KeywordChoice STRCMP = new C_KeywordChoice("strcmp", "stricmp", "strcasecmp");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.C_Expression str1;
		public C_Expression str1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationComma comma;
		public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.C.C_Expression str2;
		public C_Expression str2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.C.Terminals.C_PunctuationChoice operator = new com.eagle.programmar.C.Terminals.C_PunctuationChoice("==", "!=", "<", ">=");
		public C_PunctuationChoice @operator = new C_PunctuationChoice("==", "!=", "<", ">=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.C.Terminals.C_Keyword ZERO = new com.eagle.programmar.C.Terminals.C_Keyword("0");
		public C_Keyword ZERO = new C_Keyword("0");

		public override void interpret(EagleInterpreter interpreter)
		{
			string left = interpreter.getStrValue(str1);
			string right = interpreter.getStrValue(str2);
			int compare = -1;
			switch (STRCMP.getValue())
			{
			case "strcmp":
			default:
				compare = string.CompareOrdinal(left, right);
				break;
			case "strcasecmp":
			case "stricmp":
				compare = string.Compare(left, right, StringComparison.OrdinalIgnoreCase);
				break;
			}

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
			AbstractExpression newStr1 = transformer.transformExpression(generator, str1);
			AbstractExpression newStr2 = transformer.transformExpression(generator, str2);

			switch (@operator.getValue())
			{
			case "==", ">=":
				return generator.newRelationalExpression(types, newStr1, EagleGenerator.RelationalEnum.EQUALS, newStr2, this);
			case "!=", "<":
				return generator.newRelationalExpression(types, newStr1, EagleGenerator.RelationalEnum.NOT_EQUALS, newStr2, this);
			}

			throw new Exception("Unexpected operator: " + @operator.getValue());
		}
	}

}
