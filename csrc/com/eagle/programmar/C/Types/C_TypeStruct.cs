// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.C.Types
{
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_Subscript = com.eagle.programmar.C.C_Subscript;
	using C_Type = com.eagle.programmar.C.C_Type;
	using C_Field_Definition = com.eagle.programmar.C.Symbols.C_Field_Definition;
	using C_Type_Definition = com.eagle.programmar.C.Symbols.C_Type_Definition;
	using C_Comment = com.eagle.programmar.C.Terminals.C_Comment;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_Number = com.eagle.programmar.C.Terminals.C_Number;
	using CMacro_StatementOrComment = com.eagle.programmar.CMacro.CMacro_StatementOrComment;
	using CMacro_Syntax = com.eagle.programmar.CMacro.CMacro_Syntax;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using PunctuationStar = com.eagle.tokens.punctuation.PunctuationStar;

	public class C_TypeStruct : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.C.Terminals.C_Comment> comments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_Keyword STRUCT = new com.eagle.programmar.C.Terminals.C_Keyword("struct");
		public C_Keyword STRUCT = new C_Keyword("struct");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT C_Comment comment1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT C_Type_Definition def;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT C_Comment comment2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT C_StructBody body;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT PunctuationSemicolon semicolon;
		public  OPT;

		public class C_StructBody : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
			public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_Comment comment3;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<C_FieldOrComment> fields;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
			public PunctuationRightBrace rightBrace;
		}

		public class C_FieldOrComment : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Comment XXcomment;
			public C_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_FunctionPointer XXfunctionPtr;
			public C_FunctionPointer XXfunctionPtr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST C_TypeUnion XXunion;
			public C_TypeUnion XXunion;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.CMacro.CMacro_Syntax.class) com.eagle.programmar.CMacro.CMacro_StatementOrComment XXmacro;
			public @SYNTAX(typeof(CMacro_Syntax)) CMacro_StatementOrComment XXmacro;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_Field extends com.eagle.tokens.TokenSequence
			public static class C_Field extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.C_Type jtype;
				public C_Type jtype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Symbols.C_Field_Definition id;
				public C_Field_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<C_TypeAttributes> attributes;
				public @OPT TokenList<C_TypeAttributes> attributes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT C_TypeNumberOfBits bits;
				public @OPT C_TypeNumberOfBits bits;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<com.eagle.programmar.C.C_Subscript> subscripts;
				public @OPT TokenList<C_Subscript> subscripts;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT C_FieldInitialValue initialValue;
				public @OPT C_FieldInitialValue initialValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<C_MoreFields> more;
				public @OPT TokenList<C_MoreFields> more;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
				public PunctuationSemicolon semicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT TokenList<com.eagle.programmar.C.Terminals.C_Comment> comments;
				public @OPT TokenList<C_Comment> comments;

				public static class C_TypeNumberOfBits extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
					public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_Number bits;
					public C_Number bits;
				}

				public static class C_MoreFields extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
					public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.C.Terminals.C_Comment> comments;
					public @OPT TokenList<C_Comment> comments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.tokens.punctuation.PunctuationStar> stars;
					public @OPT TokenList<PunctuationStar> stars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.C.Symbols.C_Field_Definition id;
					public C_Field_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<com.eagle.programmar.C.C_Subscript> subscripts;
					public @OPT TokenList<C_Subscript> subscripts;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT C_FieldInitialValue initialValue;
					public @OPT C_FieldInitialValue initialValue;
				}

				public static class C_FieldInitialValue extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
					public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.C_Expression expression;
					public C_Expression expression;
				}
			}
		}
	}
}
