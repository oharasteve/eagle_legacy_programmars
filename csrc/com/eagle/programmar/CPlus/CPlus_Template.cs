// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 14, 2022

namespace com.eagle.programmar.CPlus
{
	using C_Function = com.eagle.programmar.C.C_Function;
	using C_Type = com.eagle.programmar.C.C_Type;
	using C_Variable_Definition = com.eagle.programmar.C.Symbols.C_Variable_Definition;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_KeywordChoice = com.eagle.programmar.C.Terminals.C_KeywordChoice;
	using C_Punctuation = com.eagle.programmar.C.Terminals.C_Punctuation;
	using CPlus_Class_Definition = com.eagle.programmar.CPlus.Symbols.CPlus_Class_Definition;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class CPlus_Template : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword TEMPLATE = new com.eagle.programmar.C.Terminals.C_Keyword("template");
		public C_Keyword TEMPLATE = new C_Keyword("template");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_Punctuation less = new com.eagle.programmar.C.Terminals.C_Punctuation("<");
		public C_Punctuation less = new C_Punctuation("<");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<CPlus_TemplateElement, com.eagle.tokens.punctuation.PunctuationComma> elements;
		public SeparatedList<CPlus_TemplateElement, PunctuationComma> elements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.C.Terminals.C_Punctuation greater = new com.eagle.programmar.C.Terminals.C_Punctuation(">");
		public C_Punctuation greater = new C_Punctuation(">");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT CPlus_TemplateWhat what;
		public  OPT;

		public class CPlus_TemplateElement : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_KeywordChoice CLASS = new com.eagle.programmar.C.Terminals.C_KeywordChoice("class", "typename");
			public C_KeywordChoice CLASS = new C_KeywordChoice("class", "typename");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CPlus_TemplateEquals equals;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<CPlus_TemplateClass> clsList;
			public TokenList<CPlus_TemplateClass> clsList;
		}

		public class CPlus_TemplateEquals : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_Variable_Definition var;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
		}

		public class CPlus_TemplateClass : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Class_Definition XXcls;
			public CPlus_Class_Definition XXcls;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Punctuation XXellipsis = new com.eagle.programmar.C.Terminals.C_Punctuation("...");
			public C_Punctuation XXellipsis = new C_Punctuation("...");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST C_Type XXtype;
			public C_Type XXtype;
		}

		public class CPlus_TemplateWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Function XXfunc;
			public C_Function XXfunc;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Operator XXoperator;
			public CPlus_Operator XXoperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Class XXcls;
			public CPlus_Class XXcls;
		}
	}

}
