// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.Algol68
{
	using Algol68_KeywordChoice = com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Algol68_Type : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_KeywordChoice XXprimitives = new com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice("BOOL", "INT", "DOUBLE", "STRING", "VOID");
		public Algol68_KeywordChoice XXprimitives = new Algol68_KeywordChoice("BOOL", "INT", "DOUBLE", "STRING", "VOID");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Algol68_ArrayType extends com.eagle.tokens.TokenSequence
		public class Algol68_ArrayType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Algol68_Type type;
			public Algol68_Type type;
		}

		public static TypeEnum findType(Algol68_Type type)
		{
			if (type.getWhich() is Algol68_ArrayType)
			{
				return TypeEnum.ARRAY;
			}

			Algol68_KeywordChoice typeName = (Algol68_KeywordChoice) type.getWhich();
			switch (typeName.getValue().ToUpper())
			{
			case "BOOL":
				return TypeEnum.BOOLEAN;
			case "INT":
				return TypeEnum.INTEGER;
			case "DOUBLE":
				return TypeEnum.DOUBLE;
			case "STRING":
				return TypeEnum.STRING;
			case "VOID":
				return TypeEnum.VOID;
			default:
				return TypeEnum.OTHER;
			}
		}
	}

}
