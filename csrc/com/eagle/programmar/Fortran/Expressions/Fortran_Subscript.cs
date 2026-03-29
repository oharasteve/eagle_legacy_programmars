// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Fortran.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Fortran_Expression = com.eagle.programmar.Fortran.Fortran_Expression;
	using Fortran_Identifier_Reference = com.eagle.programmar.Fortran.Symbols.Fortran_Identifier_Reference;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Fortran_Subscript : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Fortran.Symbols.Fortran_Identifier_Reference variable;
		public Fortran_Identifier_Reference variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.Fortran.Fortran_Expression, com.eagle.tokens.punctuation.PunctuationColon> args;
		public SeparatedList<Fortran_Expression, PunctuationColon> args;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.findSymbol(variable.ToString());
			if (value.isString() && args.getPrimaryCount() == 2)
			{
				string str = value.forceStringValue();
				int len = str.Length;
				int sc = interpreter.getIntValue(args.getPrimaryElement(0));
				int ec = interpreter.getIntValue(args.getPrimaryElement(1));
				if (ec > len)
				{
					ec = len;
				}
				string substr = str.Substring(sc - 1, ec - (sc - 1));
				interpreter.pushStr(substr);
			}
			else
			{
				throw new Exception("Unable to handle subscript on " + variable.ToString());
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (args.getPrimaryCount() != 2)
			{
				return null;
			}

			AbstractExpression theExpr = generator.newVariableExpression(variable.ToString(), null, null, this);
			AbstractExpression sc = transformer.transformExpression(generator, args.getPrimaryElement(0));
			AbstractExpression ec = transformer.transformExpression(generator, args.getPrimaryElement(1));
			return generator.newSubstringFunction(theExpr, sc, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ONE, EagleGenerator.SubstringECEnum.GIVEN_EC, ec, true, this);
		}
	}

}
