// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

namespace com.eagle.programmar.Fortran
{
	using Fortran_Keyword = com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
	using Fortran_KeywordChoice = com.eagle.programmar.Fortran.Terminals.Fortran_KeywordChoice;
	using Fortran_Number = com.eagle.programmar.Fortran.Terminals.Fortran_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Fortran_Type : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Fortran_DataType dataType;
		public Fortran_DataType dataType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Fortran_Dimension dimension;
		public  OPT;

		public class Fortran_DataType : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_KeywordChoice XXINTEGER = new com.eagle.programmar.Fortran.Terminals.Fortran_KeywordChoice("INTEGER", "LOGICAL");
			public Fortran_KeywordChoice XXINTEGER = new Fortran_KeywordChoice("INTEGER", "LOGICAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_CharacterType XXcharType;
			public Fortran_CharacterType XXcharType;
		}

		public class Fortran_CharacterType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("6j4m0vn7r/index.html") com.eagle.programmar.Fortran.Terminals.Fortran_Keyword CHARACTER = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("CHARACTER");
			public @DOC("6j4m0vn7r/index.html") Fortran_Keyword CHARACTER = new Fortran_Keyword("CHARACTER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Fortran.Terminals.Fortran_Keyword LEN = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("LEN");
			public Fortran_Keyword LEN = new Fortran_Keyword("LEN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Fortran.Terminals.Fortran_Number len;
			public Fortran_Number len;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static class Fortran_Dimension extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("6j4m0vn8a/index.html") com.eagle.programmar.Fortran.Terminals.Fortran_Keyword DIMENSION = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("DIMENSION");
			public @DOC("6j4m0vn8a/index.html") Fortran_Keyword DIMENSION = new Fortran_Keyword("DIMENSION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Fortran.Terminals.Fortran_Number len;
			public Fortran_Number len;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static AbstractType findType(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, Fortran_Type type)
		{
			EagleGenerator.TypeEnum newType = EagleGenerator.TypeEnum.OTHER;

			AbstractToken which = type.dataType.getWhich();
			if (which is Fortran_CharacterType)
			{
				if (type.dimension != null && type.dimension.isPresent())
				{
					newType = EagleGenerator.TypeEnum.ARRAY;
				}
				else
				{
					newType = EagleGenerator.TypeEnum.STRING;
				}
			}
			else if (which is Fortran_KeywordChoice)
			{
				Fortran_KeywordChoice @base = (Fortran_KeywordChoice) which;
				switch (@base.getValue().ToUpper())
				{
				case "LOGICAL":
					newType = EagleGenerator.TypeEnum.BOOLEAN;
					break;
				case "INTEGER":
					newType = EagleGenerator.TypeEnum.INTEGER;
					break;
				}
			}

			return generator.transformType(newType, null, null);
		}
	}

}
