// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.CSharp.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using CSharp_Argument = com.eagle.programmar.CSharp.CSharp_Argument;
	using CSharp_ArgumentOut = com.eagle.programmar.CSharp.CSharp_Argument.CSharp_ArgumentOut;
	using CSharp_ArgumentList = com.eagle.programmar.CSharp.CSharp_ArgumentList;
	using CSharp_MoreArguments = com.eagle.programmar.CSharp.CSharp_ArgumentList.CSharp_MoreArguments;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Type = com.eagle.programmar.CSharp.CSharp_Type;
	using CSharp_ArrayType = com.eagle.programmar.CSharp.CSharp_Type.CSharp_ArrayType;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class CSharp_ClassCreationWithInitializers : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword NEW = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("new");
		public CSharp_Keyword NEW = new CSharp_Keyword("new");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.CSharp_Type cstype;
		public CSharp_Type cstype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @NOSPACE CSharp_ArgumentList valueList;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE PunctuationRightBrace rightBrace;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleArray array = new EagleArray();

			if (valueList.arg.isPresent())
			{
				AbstractToken token = valueList.arg.getWhich();
				if (token is CSharp_Argument.CSharp_ArgumentOut)
				{
					CSharp_Argument.CSharp_ArgumentOut arg = (CSharp_Argument.CSharp_ArgumentOut) token;
					array.addValue(interpreter.getEagleValue(arg.arg));
				}
			}

			if (valueList.moreArgs.isPresent())
			{
				foreach (CSharp_ArgumentList.CSharp_MoreArguments more in valueList.moreArgs._elements)
				{
					AbstractToken token = more.arg.getWhich();
					if (token is CSharp_Argument.CSharp_ArgumentOut)
					{
						CSharp_Argument.CSharp_ArgumentOut arg = (CSharp_Argument.CSharp_ArgumentOut) token;
						array.addValue(interpreter.getEagleValue(arg.arg));
					}
				}
			}

			interpreter.pushEagleValue(array);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractExpression> exprs = new List<AbstractExpression>();

			if (valueList.arg.isPresent())
			{
				CSharp_Expression expr1 = valueList.arg.getExpression();
				exprs.Add(transformer.transformExpression(generator, expr1));
			}

			if (valueList.moreArgs.isPresent())
			{
				foreach (CSharp_ArgumentList.CSharp_MoreArguments more in valueList.moreArgs._elements)
				{
					CSharp_Expression expr2 = more.arg.getExpression();
					exprs.Add(transformer.transformExpression(generator, expr2));
				}
			}

			return generator.newArrayExpression(exprs, this);
		}

		public static CSharp_Expression generateArray(List<AbstractExpression> exprs, AbstractToken source)
		{
			CSharp_ClassCreationWithInitializers creat = new CSharp_ClassCreationWithInitializers();
			// Want to end up with: new string[] {"abc", "def"}
			creat.cstype = CSharp_Type.newPrimitiveType("string");

			CSharp_Type.CSharp_ArrayType array = new CSharp_Type.CSharp_ArrayType();
			array.leftBracket = new PunctuationLeftBracket();
			array.rightBracket = new PunctuationRightBracket();

			creat.cstype.arrayTypes = new TokenList<CSharp_Type.CSharp_ArrayType>();
			creat.cstype.arrayTypes.setPresent(true);
			creat.cstype.arrayTypes.addToken(array);

			creat.leftBrace = new PunctuationLeftBrace();
			creat.rightBrace = new PunctuationRightBrace();
			creat.valueList = new CSharp_ArgumentList();
			creat.valueList.setPresent(true);

			for (int i = 0; i < exprs.Count; i++)
			{
				CSharp_Argument.CSharp_ArgumentOut argOut = new CSharp_Argument.CSharp_ArgumentOut();
				argOut.arg = (CSharp_Expression) exprs[i];
				CSharp_Argument arg = new CSharp_Argument();
				arg.setWhich(argOut);

				if (i == 0)
				{
					creat.valueList.arg = arg;
					creat.valueList.arg.setPresent(true);
				}
				else
				{
					if (creat.valueList.moreArgs == null)
					{
						creat.valueList.moreArgs = new TokenList<CSharp_ArgumentList.CSharp_MoreArguments>();
					}
					CSharp_ArgumentList.CSharp_MoreArguments more = new CSharp_ArgumentList.CSharp_MoreArguments();
					more.comma = new PunctuationComma();
					more.arg = arg;
					more.arg.setPresent(true);
					creat.valueList.moreArgs.addToken(more);
				}
			}

			creat.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(creat);
		}
	}

}
