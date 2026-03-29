// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Java.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using Java_ArgumentList = com.eagle.programmar.Java.Java_ArgumentList;
	using Java_MoreArguments = com.eagle.programmar.Java.Java_ArgumentList.Java_MoreArguments;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Type = com.eagle.programmar.Java.Java_Type;
	using Java_ArrayType = com.eagle.programmar.Java.Java_Type.Java_ArrayType;
	using Java_TypeName = com.eagle.programmar.Java.Java_Type.Java_TypeName;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using Java_KeywordChoice = com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
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

	public class Java_ClassCreationWithInitializers : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Keyword NEW = new com.eagle.programmar.Java.Terminals.Java_Keyword("new");
		public Java_Keyword NEW = new Java_Keyword("new");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Java_Type jtype;
		public Java_Type jtype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Java_ArgumentList valueList;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleArray array = new EagleArray();

			if (valueList.arg.isPresent())
			{
				Java_Expression expr = valueList.arg;
				array.addValue(interpreter.getEagleValue(expr));
			}

			if (valueList.moreArgs.isPresent())
			{
				foreach (Java_ArgumentList.Java_MoreArguments more in valueList.moreArgs._elements)
				{
					array.addValue(interpreter.getEagleValue(more.arg));
				}
			}

			interpreter.pushEagleValue(array);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractExpression> exprs = new List<AbstractExpression>();

			if (valueList.arg.isPresent())
			{
				exprs.Add(transformer.transformExpression(generator, valueList.arg));
			}

			if (valueList.moreArgs.isPresent())
			{
				foreach (Java_ArgumentList.Java_MoreArguments more in valueList.moreArgs._elements)
				{
					exprs.Add(transformer.transformExpression(generator, more.arg));
				}
			}

			return generator.newArrayExpression(exprs, this);
		}

		public static Java_Expression generateArray(List<AbstractExpression> exprs, AbstractToken source)
		{
			Java_ClassCreationWithInitializers creat = new Java_ClassCreationWithInitializers();

			// Want to end up with: new String[] {"abc", "def"}
			creat.jtype = new Java_Type();
			Java_KeywordChoice str = new Java_KeywordChoice("String");
			creat.jtype.typeName = new Java_Type.Java_TypeName();
			creat.jtype.typeName.setWhich(str);

			Java_Type.Java_ArrayType array = new Java_Type.Java_ArrayType();
			array.leftBracket = new PunctuationLeftBracket();
			array.rightBracket = new PunctuationRightBracket();

			creat.jtype.arrayTypes = new TokenList<Java_Type.Java_ArrayType>();
			creat.jtype.arrayTypes.setPresent(true);
			creat.jtype.arrayTypes.addToken(array);

			creat.leftBrace = new PunctuationLeftBrace();
			creat.rightBrace = new PunctuationRightBrace();
			creat.valueList = new Java_ArgumentList();
			creat.valueList.setPresent(true);

			for (int i = 0; i < exprs.Count; i++)
			{
				if (i == 0)
				{
					creat.valueList.arg = (Java_Expression) exprs[0];
				}
				else
				{
					if (creat.valueList.moreArgs == null)
					{
						creat.valueList.moreArgs = new TokenList<Java_ArgumentList.Java_MoreArguments>();
						creat.valueList.moreArgs.setPresent(true);
					}
					Java_ArgumentList.Java_MoreArguments more = new Java_ArgumentList.Java_MoreArguments();
					more.comma = new PunctuationComma();
					more.arg = (Java_Expression) exprs[i];
					creat.valueList.moreArgs.addToken(more);
				}
			}

			creat.setTransformationSource(source);
			return Java_Generator.wrapExpression(creat);
		}
	}

}
