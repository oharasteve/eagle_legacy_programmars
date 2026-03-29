// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2015

namespace com.eagle.programmar.CPlus
{
	using C_Generic = com.eagle.programmar.C.C_Generic;
	using C_ParenthesizedExpressions = com.eagle.programmar.C.C_ParenthesizedExpressions;
	using C_Program = com.eagle.programmar.C.C_Program;
	using C_StatementOrComment = com.eagle.programmar.C.C_Program.C_StatementOrComment;
	using C_Identifier_Reference = com.eagle.programmar.C.Symbols.C_Identifier_Reference;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_KeywordChoice = com.eagle.programmar.C.Terminals.C_KeywordChoice;
	using C_Punctuation = com.eagle.programmar.C.Terminals.C_Punctuation;
	using CPlus_NamespaceList = com.eagle.programmar.CPlus.CPlus_Namespace.CPlus_NamespaceList;
	using CPlus_Class_Definition = com.eagle.programmar.CPlus.Symbols.CPlus_Class_Definition;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class CPlus_Class : TokenSequence, AbstractClass
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_Keyword FRIEND = new com.eagle.programmar.C.Terminals.C_Keyword("friend");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_KeywordChoice CLASS = new com.eagle.programmar.C.Terminals.C_KeywordChoice("class", "struct");
		public C_KeywordChoice CLASS = new C_KeywordChoice("class", "struct");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<CPlus_ClassModifier> modifiers;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CPlus_NamespaceList namespaces;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CPlus.Symbols.CPlus_Class_Definition className;
		public CPlus_Class_Definition className;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT C_Generic generic;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT C_Keyword FINAL = new com.eagle.programmar.C.Terminals.C_Keyword("final");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT CPlus_ClassExtendList extendsClasses;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) CPlus_ClassBody body;
		public CPlus_ClassBody body;

		public class CPlus_ClassModifier : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_KeywordChoice modifier = new com.eagle.programmar.C.Terminals.C_KeywordChoice(com.eagle.programmar.C.C_Program.getModifiers());
			public C_KeywordChoice modifier = new C_KeywordChoice(C_Program.Modifiers);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_ParenthesizedExpressions args;
			public  OPT;
		}

		public class CPlus_ClassBody : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationSemicolon XXsemicolon;
			public PunctuationSemicolon XXsemicolon;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CPlus_ClassBlockBody extends com.eagle.tokens.TokenSequence
			public class CPlus_ClassBlockBody : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
				public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CPlus_ClassElement> elements;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
				public PunctuationRightBrace rightBrace;
			}
		}

		public class CPlus_ClassElement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST CPlus_Constructor XXconstructor;
			public CPlus_Constructor XXconstructor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST CPlus_Operator XXoperator;
			public CPlus_Operator XXoperator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST CPlus_Data XXdata;
			public CPlus_Data XXdata;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Method XXmethod;
			public CPlus_Method XXmethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Extern XXexternC;
			public CPlus_Extern XXexternC;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Class XXinnerClass;
			public CPlus_Class XXinnerClass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Using XXusing;
			public CPlus_Using XXusing;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Template XXtemplate;
			public CPlus_Template XXtemplate;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST C_StatementOrComment XXc_stmt;
			public C_StatementOrComment XXc_stmt;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CPlus_ClassPublicPrivate extends com.eagle.tokens.TokenSequence
			public class CPlus_ClassPublicPrivate : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_KeywordChoice PUBLIC = new com.eagle.programmar.C.Terminals.C_KeywordChoice("public", "private", "protected");
				public C_KeywordChoice PUBLIC = new C_KeywordChoice("public", "private", "protected");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
				public PunctuationColon colon;
			}
		}

		public class CPlus_ClassExtendList : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<CPlus_ClassExtends, com.eagle.tokens.punctuation.PunctuationComma> extendsClasses;
			public SeparatedList<CPlus_ClassExtends, PunctuationComma> extendsClasses;

			public class CPlus_ClassExtends : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_Keyword PUBLIC = new com.eagle.programmar.C.Terminals.C_Keyword("public");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_Punctuation colonColon = new com.eagle.programmar.C.Terminals.C_Punctuation("::");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<CPlus_ExtendsNamespace> extendsNamespace;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.C.Symbols.C_Identifier_Reference otherClass;
				public C_Identifier_Reference otherClass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT C_Generic generic;
				public  OPT;

				public class CPlus_ExtendsNamespace : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Symbols.C_Identifier_Reference otherNamespace;
					public C_Identifier_Reference otherNamespace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_Punctuation colonColon = new com.eagle.programmar.C.Terminals.C_Punctuation("::");
					public C_Punctuation colonColon = new C_Punctuation("::");
				}
			}
		}
	}

}
