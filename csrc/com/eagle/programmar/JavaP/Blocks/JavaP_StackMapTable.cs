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
	using JavaP_Comment = com.eagle.programmar.JavaP.Terminals.JavaP_Comment;
	using JavaP_EndOfLine = com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
	using JavaP_Keyword = com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
	using JavaP_KeywordChoice = com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
	using JavaP_Number = com.eagle.programmar.JavaP.Terminals.JavaP_Number;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class JavaP_StackMapTable : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword STACKMAP = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("StackMapTable");
		public JavaP_Keyword STACKMAP = new JavaP_Keyword("StackMapTable");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword NUMBER_OF_ENTRIES = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("number_of_entries");
		public JavaP_Keyword NUMBER_OF_ENTRIES = new JavaP_Keyword("number_of_entries");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.JavaP.Terminals.JavaP_Number entries;
		public JavaP_Number entries;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
		public JavaP_EndOfLine eoln;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<JavaP_StackMapFrame> frames;
		public  OPT;

		public class JavaP_StackMapFrame : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword FRAMETYPE = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("frame_type");
			public JavaP_Keyword FRAMETYPE = new JavaP_Keyword("frame_type");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_Number frameType;
			public JavaP_Number frameType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT JavaP_Comment comment;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
			public JavaP_EndOfLine eoln;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT JavaP_StackMapOffset offsetDelta;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT JavaP_StackMapLocals locals;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT JavaP_StackMapStack stack;
			public  OPT;

			public class JavaP_StackMapOffset : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword OFFSETDELTA = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("offset_delta");
				public JavaP_Keyword OFFSETDELTA = new JavaP_Keyword("offset_delta");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_Number delta;
				public JavaP_Number delta;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
				public JavaP_EndOfLine eoln;
			}

			public class JavaP_StackMapLocals : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword LOCALS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("locals");
				public JavaP_Keyword LOCALS = new JavaP_Keyword("locals");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
				public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT SeparatedList<JavaP_StackMapLocal, com.eagle.tokens.punctuation.PunctuationComma> locals;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
				public PunctuationRightBracket rightBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
				public JavaP_EndOfLine eoln;

				public class JavaP_StackMapLocal : TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_KeywordChoice XXtype = new com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice("bogus", "double", "int", "long", "top");
					public JavaP_KeywordChoice XXtype = new JavaP_KeywordChoice("bogus", "double", "int", "long", "top");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class JavaP_StackMapLocalClass extends com.eagle.tokens.TokenSequence
					public class JavaP_StackMapLocalClass : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword CLASS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("class");
						public JavaP_Keyword CLASS = new JavaP_Keyword("class");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.JavaP_Value value;
						public JavaP_Value value;
					}
				}
			}

			public class JavaP_StackMapStack : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword STACK = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("stack");
				public JavaP_Keyword STACK = new JavaP_Keyword("stack");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
				public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.SeparatedList<JavaP_StackMapStackClass, com.eagle.tokens.punctuation.PunctuationComma> stackClasses;
				public SeparatedList<JavaP_StackMapStackClass, PunctuationComma> stackClasses;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
				public PunctuationRightBracket rightBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
				public JavaP_EndOfLine eoln;

				public class JavaP_StackMapStackClass : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT JavaP_KeywordChoice CLASS = new com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice("class", "uninitialized");
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT JavaP_Value value;
					public  OPT;
				}
			}
		}
	}

}
