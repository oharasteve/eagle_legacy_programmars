// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

namespace com.eagle.programmar.PLI
{
	using PLI_BitLiteral = com.eagle.programmar.PLI.Terminals.PLI_BitLiteral;
	using PLI_Keyword = com.eagle.programmar.PLI.Terminals.PLI_Keyword;
	using PLI_KeywordChoice = com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
	using PLI_Literal = com.eagle.programmar.PLI.Terminals.PLI_Literal;
	using PLI_Picture = com.eagle.programmar.PLI.Terminals.PLI_Picture;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationStar = com.eagle.tokens.punctuation.PunctuationStar;

	public class PLI_Type : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationStar XXstar;
		public PunctuationStar XXstar;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_BaseType extends com.eagle.tokens.TokenChooser
		public class PLI_BaseType : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_KeywordChoice XXbase = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("COMPLEX", "FILE", "PRINT", "UNION", "VARYING");
			public PLI_KeywordChoice XXbase = new PLI_KeywordChoice("COMPLEX", "FILE", "PRINT", "UNION", "VARYING");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypePointer extends com.eagle.tokens.TokenSequence
			public class PLI_TypePointer : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword PTR = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("PTR");
				public PLI_Keyword PTR = new PLI_Keyword("PTR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PLI_Keyword BASED = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("BASED");
				public  OPT;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypeCharacter extends com.eagle.tokens.TokenSequence
			public class PLI_TypeCharacter : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PLI_TypeSize size1;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice CHARACTER = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("CHAR", "CHARACTER", "WIDECHAR");
				public PLI_KeywordChoice CHARACTER = new PLI_KeywordChoice("CHAR", "CHARACTER", "WIDECHAR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PLI_TypeSize size2;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PLI_KeywordChoice varyingOrStatic = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("BASED", "STATIC", "VARYING");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT PLI_CharInitial initialValue;
				public  OPT;

				public class PLI_CharInitial : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice INITIAL = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("INITIAL", "INIT");
					public PLI_KeywordChoice INITIAL = new PLI_KeywordChoice("INITIAL", "INIT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
					public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PLI.Terminals.PLI_Literal initialValue;
					public PLI_Literal initialValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
					public PunctuationRightParen rightParen;
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypePicture extends com.eagle.tokens.TokenSequence
			public class PLI_TypePicture : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword PIC = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("PIC");
				public PLI_Keyword PIC = new PLI_Keyword("PIC");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Terminals.PLI_Picture picture;
				public PLI_Picture picture;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypeFixedBinary extends com.eagle.tokens.TokenSequence
			public class PLI_TypeFixedBinary : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice FIXED = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("FIXED", "FLOAT");
				public PLI_KeywordChoice FIXED = new PLI_KeywordChoice("FIXED", "FLOAT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice BINARY = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("BINARY", "BIN");
				public PLI_KeywordChoice BINARY = new PLI_KeywordChoice("BINARY", "BIN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PLI_TypeSize size;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PLI_Keyword COMPLEX = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("COMPLEX");
				public  OPT;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypeFloat extends com.eagle.tokens.TokenSequence
			public class PLI_TypeFloat : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice FIXED = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("FIXED", "FLOAT");
				public PLI_KeywordChoice FIXED = new PLI_KeywordChoice("FIXED", "FLOAT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PLI_KeywordChoice DECIMAL = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("DECIMAL", "DEC");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PLI_TypeSize size;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PLI_Keyword COMPLEX = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("COMPLEX");
				public  OPT;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypeBit extends com.eagle.tokens.TokenSequence
			public class PLI_TypeBit : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PLI_TypeSize size1;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Terminals.PLI_Keyword BIT = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("BIT");
				public PLI_Keyword BIT = new PLI_Keyword("BIT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PLI_TypeSize size2;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PLI_KeywordChoice alignedOrStatic = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("STATIC", "VARYING");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT PLI_Keyword ALIGNED = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("ALIGNED");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT PLI_BitInitial initialValue;
				public  OPT;

				public class PLI_BitInitial : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword INITIAL = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("INITIAL");
					public PLI_Keyword INITIAL = new PLI_Keyword("INITIAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
					public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PLI.Terminals.PLI_BitLiteral initialValue;
					public PLI_BitLiteral initialValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
					public PunctuationRightParen rightParen;
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypeGraphic extends com.eagle.tokens.TokenSequence
			public class PLI_TypeGraphic : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword GRAPHIC = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("GRAPHIC");
				public PLI_Keyword GRAPHIC = new PLI_Keyword("GRAPHIC");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PLI_TypeSize size;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PLI_KeywordChoice varyingOrStatic = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("VARYING");
				public  OPT;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypeEntry extends com.eagle.tokens.TokenSequence
			public class PLI_TypeEntry : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword ENTRY = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("ENTRY");
				public PLI_Keyword ENTRY = new PLI_Keyword("ENTRY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<PLI_TypeEntryDetail> details;
				public  OPT;

				public class PLI_TypeEntryDetail : TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypeEntryPtr extends com.eagle.tokens.TokenSequence
					public class PLI_TypeEntryPtr : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
						public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Terminals.PLI_Keyword PTR = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("PTR");
						public PLI_Keyword PTR = new PLI_Keyword("PTR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
						public PunctuationRightParen rightParen;
					}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypeEntryReturns extends com.eagle.tokens.TokenSequence
					public class PLI_TypeEntryReturns : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword RETURNS = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("RETURNS");
						public PLI_Keyword RETURNS = new PLI_Keyword("RETURNS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
						public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) PLI_Type type;
						public PLI_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
						public PunctuationRightParen rightParen;
					}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypeEntryOptionss extends com.eagle.tokens.TokenSequence
					public class PLI_TypeEntryOptionss : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword OPTIONS = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("OPTIONS");
						public PLI_Keyword OPTIONS = new PLI_Keyword("OPTIONS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen1;
						public PunctuationLeftParen leftParen1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PLI.Terminals.PLI_Keyword ASM = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("ASM");
						public PLI_Keyword ASM = new PLI_Keyword("ASM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.PLI.Terminals.PLI_Keyword LINKAGE = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("LINKAGE");
						public PLI_Keyword LINKAGE = new PLI_Keyword("LINKAGE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen2;
						public PunctuationLeftParen leftParen2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.PLI.Terminals.PLI_Keyword SYSTEM = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("SYSTEM");
						public PLI_Keyword SYSTEM = new PLI_Keyword("SYSTEM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationRightParen rightParen2;
						public PunctuationRightParen rightParen2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationRightParen rightParen1;
						public PunctuationRightParen rightParen1;
					}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypeEntryExternal extends com.eagle.tokens.TokenSequence
					public class PLI_TypeEntryExternal : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword EXTERNAL = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("EXTERNAL");
						public PLI_Keyword EXTERNAL = new PLI_Keyword("EXTERNAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
						public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PLI.Terminals.PLI_Literal literal;
						public PLI_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
						public PunctuationRightParen rightParen;

					}
				}
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypeSize extends com.eagle.tokens.TokenSequence
		public class PLI_TypeSize : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<PLI_TypeSizeContents, com.eagle.tokens.punctuation.PunctuationComma> typeSizeContents;
			public SeparatedList<PLI_TypeSizeContents, PunctuationComma> typeSizeContents;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;

			public class PLI_TypeSizeContents : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationStar XXstar;
				public PunctuationStar XXstar;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_TypeSizeNormal extends com.eagle.tokens.TokenSequence
				public class PLI_TypeSizeNormal : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) PLI_Expression size1;
					public PLI_Expression size1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationComma comma;
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PLI_Expression size2;
					public  OPT;
				}
			}
		}
	}

}
