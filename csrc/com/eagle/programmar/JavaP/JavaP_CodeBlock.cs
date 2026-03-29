// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

namespace com.eagle.programmar.JavaP
{
	using JavaP_CodeExceptions = com.eagle.programmar.JavaP.Blocks.JavaP_CodeExceptions;
	using JavaP_CodeLineNumbers = com.eagle.programmar.JavaP.Blocks.JavaP_CodeLineNumbers;
	using JavaP_CodeLocalValues = com.eagle.programmar.JavaP.Blocks.JavaP_CodeLocalValues;
	using JavaP_CodeStackInfo = com.eagle.programmar.JavaP.Blocks.JavaP_CodeStackInfo;
	using JavaP_StackMapTable = com.eagle.programmar.JavaP.Blocks.JavaP_StackMapTable;
	using JavaP_EndOfLine = com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
	using JavaP_Keyword = com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;

	public class JavaP_CodeBlock : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword CODE = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("Code");
		public JavaP_Keyword CODE = new JavaP_Keyword("Code");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
		public JavaP_EndOfLine eoln;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<JavaP_CodeDetails> details;
		public  OPT;

		public class JavaP_CodeDetails : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_CodeLocalValues XXlocalValues;
			public JavaP_CodeLocalValues XXlocalValues;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_CodeStackInfo XXstackInfo;
			public JavaP_CodeStackInfo XXstackInfo;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_CodeLineNumbers XXlineNumbers;
			public JavaP_CodeLineNumbers XXlineNumbers;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_StackMapTable XXstackMap;
			public JavaP_StackMapTable XXstackMap;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_CodeExceptions XXcodeExceptions;
			public JavaP_CodeExceptions XXcodeExceptions;
		}
	}

}
