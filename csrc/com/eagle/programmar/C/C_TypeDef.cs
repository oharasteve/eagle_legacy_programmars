// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

namespace com.eagle.programmar.C
{
	using C_Function_ParameterDefs = com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
	using C_Function_Definition = com.eagle.programmar.C.Symbols.C_Function_Definition;
	using C_Type_Definition = com.eagle.programmar.C.Symbols.C_Type_Definition;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_TypeAttributes = com.eagle.programmar.C.Types.C_TypeAttributes;
	using CMacro_StatementOrComment = com.eagle.programmar.CMacro.CMacro_StatementOrComment;
	using CMacro_Syntax = com.eagle.programmar.CMacro.CMacro_Syntax;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using PunctuationStar = com.eagle.tokens.punctuation.PunctuationStar;

	public class C_TypeDef : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_Keyword EXTENSION = new com.eagle.programmar.C.Terminals.C_Keyword("__extension__");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_Keyword TYPEDEF = new com.eagle.programmar.C.Terminals.C_Keyword("typedef");
		public C_Keyword TYPEDEF = new C_Keyword("typedef");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT C_Keyword INTERFACE = new com.eagle.programmar.C.Terminals.C_Keyword("interface");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) C_TypeDef_What what;
		public C_TypeDef_What what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<com.eagle.programmar.C.Types.C_TypeAttributes> attributes;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class C_TypeDef_What : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.CMacro.CMacro_Syntax.class) com.eagle.programmar.CMacro.CMacro_StatementOrComment XXmacro;
			public @SYNTAX(typeof(CMacro_Syntax)) CMacro_StatementOrComment XXmacro;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_TypeDef_Data extends com.eagle.tokens.TokenSequence
			public static class C_TypeDef_Data extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) C_Type type;
				public C_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.tokens.punctuation.PunctuationStar> stars;
				public @OPT TokenList<PunctuationStar> stars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Symbols.C_Type_Definition typeName;
				public C_Type_Definition typeName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT C_Subscript subscript;
				public @OPT C_Subscript subscript;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<C_TypeDefMore> more;
				public @OPT TokenList<C_TypeDefMore> more;

				public static class C_TypeDefMore extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
					public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.tokens.punctuation.PunctuationStar> stars;
					public @OPT TokenList<PunctuationStar> stars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Symbols.C_Type_Definition typeName;
					public C_Type_Definition typeName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT C_Subscript subscript;
					public @OPT C_Subscript subscript;
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_TypeDef_Union extends com.eagle.tokens.TokenSequence
			public static class C_TypeDef_Union extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword UNION = new com.eagle.programmar.C.Terminals.C_Keyword("union");
				public C_Keyword UNION = new C_Keyword("union");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Symbols.C_Type_Definition unionName;
				public C_Type_Definition unionName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Symbols.C_Type_Definition typeName;
				public C_Type_Definition typeName;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST static class C_TypeDef_Function extends com.eagle.tokens.TokenSequence
			public static class C_TypeDef_Function extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) C_Type returnType;
				public C_Type returnType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.tokens.punctuation.PunctuationStar> stars;
				public @OPT TokenList<PunctuationStar> stars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.C.Symbols.C_Function_Definition funcName;
				public C_Function_Definition funcName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.C.C_Function.C_Function_ParameterDefs params;
				public C_Function_ParameterDefs @params;
			}

			// Just like C_TypeDef_Function but no parens ... I didn't know this was valid
			// syntax!
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class C_TypeDef_NoParensFunction extends com.eagle.tokens.TokenSequence
			public static class C_TypeDef_NoParensFunction extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) C_Type returnType;
				public C_Type returnType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Symbols.C_Function_Definition funcName;
				public C_Function_Definition funcName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.C_Function.C_Function_ParameterDefs params;
				public C_Function_ParameterDefs @params;
			}
		}
	}

}
