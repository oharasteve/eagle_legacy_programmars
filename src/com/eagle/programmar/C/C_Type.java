// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.C_Data.C_FunctionPointer;
import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypePrimitive.C_TypeStar;
import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypeStruct.C_FieldOrComment;
import com.eagle.programmar.C.Symbols.C_Field_Definition;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Symbols.C_Type_Definition;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_Type extends TokenSequence implements AbstractType
{
	public @S(10) @OPT C_Keyword EXTENSION = new C_Keyword("__extension__");
	public @S(20) @OPT C_Keyword VOLATILE = new C_Keyword("volatile");
	public @S(30) C_TypeBase base;
	public @S(40) @OPT C_TypeGeneric generic;
	public @S(50) @OPT C_TypeFunction function;
	public @S(60) @OPT C_Keyword CONST = new C_Keyword("const");
	public @S(70) @OPT TokenList<C_TypeStar> stars;
	
	public static class C_TypeBase extends TokenChooser
	{
		public @CHOICE C_Enum enumeration;
		
		public @FIRST static class C_NamespaceType extends TokenSequence
		{
			public @S(10) C_Identifier_Reference namespace;
			public @S(20) C_Punctuation colonColon = new C_Punctuation("::");
			public @S(30) C_TypeBase typebase;
		}

		public @CHOICE static class C_TypeUnion extends TokenSequence
		{
			public @S(10) C_Keyword UNION = new C_Keyword("union");
			public @S(20) @OPT C_Type_Definition def;
			public @S(30) PunctuationLeftBrace leftBrace;
			public @S(40) @OPT TokenList<C_FieldOrComment> fields;
			public @S(50) PunctuationRightBrace rightBrace;
			public @S(60) @OPT PunctuationSemicolon semicolon;
		}
		
		public @LAST static class C_TypeSimpleUnion extends TokenSequence
		{
			public @S(10) C_Keyword UNION = new C_Keyword("union");
			public @S(20) C_Type_Definition def;
		}
		
		public @CHOICE static class C_TypeStruct extends TokenSequence
		{
			public @S(10) @OPT TokenList<C_Comment> comments;
			public @S(20) C_Keyword STRUCT = new C_Keyword("struct");
			public @S(30) @OPT C_Comment comment1;
			public @S(40) @OPT C_Type_Definition def;
			public @S(50) @OPT C_Comment comment2;
			public @S(60) PunctuationLeftBrace leftBrace;
			public @S(70) @OPT C_Comment comment3;
			public @S(80) @OPT TokenList<C_FieldOrComment> fields;
			public @S(90) PunctuationRightBrace rightBrace;
			public @S(100) @OPT PunctuationSemicolon semicolon;
			
			public static class C_FieldOrComment extends TokenChooser
			{
				public @CHOICE C_Comment comment;
				public @CHOICE C_FunctionPointer functionPtr;
				public @LAST C_TypeUnion union;
				public @CHOICE @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro;
				
				public @CHOICE static class C_Field extends TokenSequence
				{
					public @S(10) C_Type jtype;
					public @S(20) C_Field_Definition id;
					public @S(30) @OPT TokenList<C_Subscript> subscripts;
					public @S(40) @OPT C_FieldInitialValue initialValue;
					public @S(50) @OPT TokenList<C_MoreFields> more;
					public @S(60) @NOSPACE PunctuationSemicolon semicolon;
					public @S(70) @OPT TokenList<C_Comment> comments;
					
					public static class C_MoreFields extends TokenSequence
					{
						public @S(10) PunctuationComma comma;
						public @S(20) @OPT TokenList<C_Comment> comments;
						public @S(30) @OPT TokenList<PunctuationStar> stars;
						public @S(40) C_Field_Definition id;
						public @S(50) @OPT TokenList<C_Subscript> subscripts;
						public @S(60) @OPT C_FieldInitialValue initialValue;
					}
					
					public static class C_FieldInitialValue extends TokenSequence
					{
						public @S(10) PunctuationEquals equals;
						public @S(20) C_Expression expression;
					}
				}
			}
		}

		public @CHOICE static class C_TypePrimitive extends TokenSequence
		{
			public @S(10) @OPT C_Keyword CONST = new C_Keyword("const");
			public @S(20) @OPT C_KeywordChoice UNSIGNED = new C_KeywordChoice("signed", "unsigned");
			public @S(30) C_KeywordChoice primitive = new C_KeywordChoice(C_Program.getPrimitives());
			public @S(40) @OPT C_Keyword INT = new C_Keyword("int");
			public @S(50) @OPT TokenList<C_TypeStar> stars;
			
			public static class C_TypeStar extends TokenSequence
			{
				public @S(10) C_PunctuationChoice starAmpersand = new C_PunctuationChoice("*", "&");
			}
		}
		
		// This one isn't handled by C_TypePrimitive
		public @FIRST static class C_TypeShortUnsignedInt extends TokenSequence
		{
			public @S(10) @OPT C_KeywordChoice UNSIGNED1 = new C_KeywordChoice("signed", "unsigned");
			public @S(20) C_KeywordChoice SHORT = new C_KeywordChoice("long", "short");
			public @S(30) @OPT C_Keyword LONG = new C_Keyword("long");
			public @S(40) @OPT C_KeywordChoice UNSIGNED2 = new C_KeywordChoice("signed", "unsigned");
			public @S(50) C_KeywordChoice INT = new C_KeywordChoice("int", "double");
			public @S(60) @OPT TokenList<C_TypeStar> stars;
		}
		
		public @CHOICE static class C_TypeUserDefined extends TokenSequence
		{
			public @S(10) @OPT C_Keyword STRUCT = new C_Keyword("struct");
			public @S(20) C_Identifier_Reference typeName;
			public @S(30) @OPT TokenList<C_TypeStar> stars;
		}
	}
	
	public static class C_TypeGeneric extends TokenSequence
	{
		public @S(10) C_Punctuation lessThan = new C_Punctuation('<');
		public @S(20) SeparatedList<C_Type, PunctuationComma> types;
		public @S(30) C_Punctuation greaterThan = new C_Punctuation('>');
	}
	
	public static class C_TypeFunction extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) PunctuationStar star;
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) C_Function_ParameterDefs params;
	}
}
