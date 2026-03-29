// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

namespace com.eagle.programmar.JavaP.Statements
{
	using JavaP_ConstantClass = com.eagle.programmar.JavaP.Constants.JavaP_ConstantClass;
	using JavaP_ConstantLong = com.eagle.programmar.JavaP.Constants.JavaP_ConstantLong;
	using JavaP_ConstantMethodHandle = com.eagle.programmar.JavaP.Constants.JavaP_ConstantMethodHandle;
	using JavaP_ConstantMethodRef = com.eagle.programmar.JavaP.Constants.JavaP_ConstantMethodRef;
	using JavaP_ConstantNameAndType = com.eagle.programmar.JavaP.Constants.JavaP_ConstantNameAndType;
	using JavaP_ConstantString = com.eagle.programmar.JavaP.Constants.JavaP_ConstantString;
	using JavaP_ConstantUtf8 = com.eagle.programmar.JavaP.Constants.JavaP_ConstantUtf8;
	using JavaP_Symbol_Definition = com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Definition;
	using JavaP_Comment = com.eagle.programmar.JavaP.Terminals.JavaP_Comment;
	using JavaP_EndOfLine = com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
	using JavaP_Keyword = com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class JavaP_ConstantPool : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword CONSTANT = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("Constant");
		public JavaP_Keyword CONSTANT = new JavaP_Keyword("Constant");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword POOL = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("pool");
		public JavaP_Keyword POOL = new JavaP_Keyword("pool");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
		public JavaP_EndOfLine eoln;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<JavaP_Constant> constants;
		public TokenList<JavaP_Constant> constants;

		public class JavaP_Constant : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT JavaP_Keyword CONST = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("const");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Definition symbol;
			public JavaP_Symbol_Definition symbol;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) JavaP_ConstantType type;
			public JavaP_ConstantType type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT PunctuationSemicolon semicolon;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT JavaP_Comment comment;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
			public JavaP_EndOfLine eoln;

			public class JavaP_ConstantType : TokenChooser
			{
				// All of these need to implement the JavaP_ConstantShowable interface
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_ConstantClass XXconstantClass;
				public JavaP_ConstantClass XXconstantClass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_ConstantLong XXconstantLong;
				public JavaP_ConstantLong XXconstantLong;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_ConstantMethodRef XXmethodRef;
				public JavaP_ConstantMethodRef XXmethodRef;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_ConstantMethodHandle XXmethodHandle;
				public JavaP_ConstantMethodHandle XXmethodHandle;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_ConstantNameAndType XXnameAndType;
				public JavaP_ConstantNameAndType XXnameAndType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_ConstantString XXconstantString;
				public JavaP_ConstantString XXconstantString;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_ConstantUtf8 XXconstantUtf8;
				public JavaP_ConstantUtf8 XXconstantUtf8;
			}
		}

		public interface JavaP_ConstantShowable
		{
			string showConstant();
		}
	}

}
