// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2022

namespace com.eagle.programmar.Javascript
{
	using Javascript_Class_Definition = com.eagle.programmar.Javascript.Symbols.Javascript_Class_Definition;
	using Javascript_Class_Reference = com.eagle.programmar.Javascript.Symbols.Javascript_Class_Reference;
	using Javascript_Function_Definition = com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
	using Javascript_Keyword = com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
	using Javascript_KeywordChoice = com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Javascript_Class : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Javascript_Keyword EXPORT = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("export");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Javascript.Terminals.Javascript_Keyword CLASS = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("class");
		public Javascript_Keyword CLASS = new Javascript_Keyword("class");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Javascript.Symbols.Javascript_Class_Definition name;
		public Javascript_Class_Definition name;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Javascript_ClassExtends extend;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<Javascript_ClassElement> elements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;

		public class Javascript_ClassExtends : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Terminals.Javascript_Keyword EXTENDS = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("extends");
			public Javascript_Keyword EXTENDS = new Javascript_Keyword("extends");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Javascript.Symbols.Javascript_Class_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> name;
			public SeparatedList<Javascript_Class_Reference, PunctuationPeriod> name;
		}

		public class Javascript_ClassElement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Method XXmethod;
			public Javascript_Method XXmethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Element XXelement;
			public Javascript_Element XXelement;
		}

		public class Javascript_Method : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Terminals.Javascript_Keyword STATIC = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("static");
			public Javascript_Keyword STATIC = new Javascript_Keyword("static");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Javascript_KeywordChoice GET = new com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice("get", "set");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition name;
			public Javascript_Function_Definition name;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Javascript_FunctionParameters params;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) Javascript_FunctionBody body;
			public Javascript_FunctionBody body;
		}
	}

}
