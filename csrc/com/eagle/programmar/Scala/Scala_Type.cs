// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Scala
{
	using Scala_KeywordChoice = com.eagle.programmar.Scala.Terminals.Scala_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Scala_Type : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_KeywordChoice XXtype = new com.eagle.programmar.Scala.Terminals.Scala_KeywordChoice("Bool", "Boolean", "Int", "String");
		public Scala_KeywordChoice XXtype = new Scala_KeywordChoice("Bool", "Boolean", "Int", "String");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Scala_TypeArray extends com.eagle.tokens.TokenSequence
		public class Scala_TypeArray : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Scala.Terminals.Scala_KeywordChoice LIST = new com.eagle.programmar.Scala.Terminals.Scala_KeywordChoice("Array", "List");
			public Scala_KeywordChoice LIST = new Scala_KeywordChoice("Array", "List");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Scala_Type subtype;
			public Scala_Type subtype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

		public static AbstractType findType(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, Scala_Type type)
		{
			EagleGenerator.TypeEnum newType;
			if (type.getWhich() is Scala_TypeArray)
			{
				newType = EagleGenerator.TypeEnum.ARRAY;
			}
			else
			{
				Scala_KeywordChoice typeName = (Scala_KeywordChoice) type.getWhich();
				switch (typeName.getValue().ToLower())
				{
				case "bool":
				case "boolean":
					newType = EagleGenerator.TypeEnum.BOOLEAN;
					break;
				case "int":
					newType = EagleGenerator.TypeEnum.INTEGER;
					break;
				case "string":
					newType = EagleGenerator.TypeEnum.STRING;
					break;
				default:
					newType = EagleGenerator.TypeEnum.OTHER;
					break;
				}
			}

			return generator.transformType(newType, null, null);
		}
	}

}
