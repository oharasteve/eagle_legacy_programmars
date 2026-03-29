// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 4, 2015

namespace com.eagle.programmar.JavaP.Blocks
{
	using JavaP_Value = com.eagle.programmar.JavaP.JavaP_Value;
	using JavaP_EndOfLine = com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
	using JavaP_HexNumber = com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber;
	using JavaP_HexNoPrefix = com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber.JavaP_HexNoPrefix;
	using JavaP_Keyword = com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
	using JavaP_KeywordChoice = com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
	using JavaP_Number = com.eagle.programmar.JavaP.Terminals.JavaP_Number;
	using JavaP_QualifiedName = com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class JavaP_CodeLocalValues : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice LOCALVARIABLES = new com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice("LocalVariableTable", "LocalVariableTypeTable");
		public JavaP_KeywordChoice LOCALVARIABLES = new JavaP_KeywordChoice("LocalVariableTable", "LocalVariableTypeTable");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) JavaP_CodeLocalHeaderChoice header;
		public JavaP_CodeLocalHeaderChoice header;

		public class JavaP_CodeLocalHeaderChoice : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class JavaP_CodeLocalHeader extends com.eagle.tokens.TokenSequence
			public class JavaP_CodeLocalHeader : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln1;
				public JavaP_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword START = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("Start");
				public JavaP_Keyword START = new JavaP_Keyword("Start");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword LENGTH = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("Length");
				public JavaP_Keyword LENGTH = new JavaP_Keyword("Length");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword SLOT = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("Slot");
				public JavaP_Keyword SLOT = new JavaP_Keyword("Slot");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword NAME = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("Name");
				public JavaP_Keyword NAME = new JavaP_Keyword("Name");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword SIGNATURE = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("Signature");
				public JavaP_Keyword SIGNATURE = new JavaP_Keyword("Signature");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln2;
				public JavaP_EndOfLine eoln2;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<JavaP_CodeLocalEntry> entries;
				public  OPT;

				public class JavaP_CodeLocalEntry : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Number start;
					public JavaP_Number start;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Terminals.JavaP_Number length;
					public JavaP_Number length;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_Number slot;
					public JavaP_Number slot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName name;
					public JavaP_QualifiedName name;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.JavaP.JavaP_Value value;
					public JavaP_Value value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
					public JavaP_EndOfLine eoln;
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class JavaP_CodeLocalLength extends com.eagle.tokens.TokenSequence
			public class JavaP_CodeLocalLength : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword LENGTH = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("length");
				public JavaP_Keyword LENGTH = new JavaP_Keyword("length");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber len;
				public JavaP_HexNumber len;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<JavaP_CodeLocalHexes> hexes;
				public TokenList<JavaP_CodeLocalHexes> hexes;

				public class JavaP_CodeLocalHexes : TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_HexNoPrefix XXhex;
					public JavaP_HexNoPrefix XXhex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_EndOfLine XXeoln;
					public JavaP_EndOfLine XXeoln;
				}
			}
		}
	}

}
