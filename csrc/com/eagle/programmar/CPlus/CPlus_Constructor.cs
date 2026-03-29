// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 10, 2022

namespace com.eagle.programmar.CPlus
{
	using C_ArgumentList = com.eagle.programmar.C.C_ArgumentList;
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_Function_ParameterDefs = com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_KeywordChoice = com.eagle.programmar.C.Terminals.C_KeywordChoice;
	using C_Punctuation = com.eagle.programmar.C.Terminals.C_Punctuation;
	using CPlus_NamespaceList = com.eagle.programmar.CPlus.CPlus_Namespace.CPlus_NamespaceList;
	using CPlus_Class_Reference = com.eagle.programmar.CPlus.Symbols.CPlus_Class_Reference;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class CPlus_Constructor : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CPlus_ConstructorWithParameters extends com.eagle.tokens.TokenSequence
		public class CPlus_ConstructorWithParameters : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_KeywordChoice VIRTUAL = new com.eagle.programmar.C.Terminals.C_KeywordChoice("virtual", "constexpr", "explicit");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_Punctuation tilde = new com.eagle.programmar.C.Terminals.C_Punctuation('~');
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT CPlus_NamespaceList nameSpaces;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CPlus.Symbols.CPlus_Class_Reference constructorName;
			public CPlus_Class_Reference constructorName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.C.C_Function.C_Function_ParameterDefs parameters;
			public C_Function_ParameterDefs parameters;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT C_Keyword OVERRIDE = new com.eagle.programmar.C.Terminals.C_Keyword("override");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT CPlus_ConstructorCallSupers callSupers;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) CPlus_ConstructorValue value;
			public CPlus_ConstructorValue value;

			public class CPlus_ConstructorCallSupers : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
				public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<CPlus_ConstructorCallSuper, com.eagle.tokens.punctuation.PunctuationComma> callSuper;
				public SeparatedList<CPlus_ConstructorCallSuper, PunctuationComma> callSuper;

				public class CPlus_ConstructorCallSuper : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CPlus.Symbols.CPlus_Class_Reference parent;
					public CPlus_Class_Reference parent;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CPlus_NamespaceList namespaces;
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
					public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT C_ArgumentList argList;
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
					public PunctuationRightParen rightParen;
				}
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CPlus_ConstructorParameterLess extends com.eagle.tokens.TokenSequence
		public class CPlus_ConstructorParameterLess : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_KeywordChoice CONST = new com.eagle.programmar.C.Terminals.C_KeywordChoice("const", "constexpr");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CPlus_NamespaceList nameSpaces;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CPlus.Symbols.CPlus_Class_Reference constructorName;
			public CPlus_Class_Reference constructorName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.C.C_Expression expr;
			public C_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
			public PunctuationSemicolon semicolon;
		}
	}

}
