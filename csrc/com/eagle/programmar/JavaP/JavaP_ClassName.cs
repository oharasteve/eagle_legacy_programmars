// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

namespace com.eagle.programmar.JavaP
{
	using JavaP_Identifier = com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
	using JavaP_LClassName = com.eagle.programmar.JavaP.Terminals.JavaP_LClassName;
	using JavaP_Punctuation = com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
	using JavaP_PunctuationChoice = com.eagle.programmar.JavaP.Terminals.JavaP_PunctuationChoice;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;
	using PunctuationStar = com.eagle.tokens.punctuation.PunctuationStar;

	public class JavaP_ClassName : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class JavaP_ClassNameL extends com.eagle.tokens.TokenSequence
		public class JavaP_ClassNameL : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT JavaP_PunctuationChoice plus = new com.eagle.programmar.JavaP.Terminals.JavaP_PunctuationChoice("[", "+", "*");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Terminals.JavaP_LClassName lClass;
			public JavaP_LClassName lClass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT JavaP_TemplatedClass template;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
			public PunctuationSemicolon semicolon;

			public class JavaP_TemplatedClass : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation lessThan = new com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation('<');
				public JavaP_Punctuation lessThan = new JavaP_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<JavaP_TemplateContents> contents;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation greaterThan = new com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation('>');
				public JavaP_Punctuation greaterThan = new JavaP_Punctuation('>');

				public class JavaP_TemplateContents : TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_ClassName XXclassName;
					public JavaP_ClassName XXclassName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationStar XXstar;
					public PunctuationStar XXstar;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class JavaP_TemplateIds extends com.eagle.tokens.TokenSequence
					public class JavaP_TemplateIds : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Identifier id;
						public JavaP_Identifier id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
						public PunctuationSemicolon semicolon;
					}
				}
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class JavaP_ClassNameNoL extends com.eagle.tokens.TokenSequence
		public class JavaP_ClassNameNoL : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.JavaP.Terminals.JavaP_Identifier, com.eagle.tokens.punctuation.PunctuationSlash> identifier;
			public SeparatedList<JavaP_Identifier, PunctuationSlash> identifier;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationSemicolon semicolon;
			public  OPT;
		}
	}

}
