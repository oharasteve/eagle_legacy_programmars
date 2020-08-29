// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.C_Data.C_FunctionPointer;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.Symbols.C_Function_Definition;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_Function extends TokenSequence
{
	public @OPT C_Keyword EXTENSION = new C_Keyword("__extension__");
	public @OPT C_KeywordChoice scope1 = new C_KeywordChoice(C_Program.getModifiers());
	public @OPT C_Comment comment1;
	public @OPT C_KeywordChoice scope2 = new C_KeywordChoice(C_Program.getModifiers());
	public C_FunctionTypeName typeName;
	public C_Function_ParameterDefs parameters;
	public @OPT TokenList<C_Comment> comments2;
	public C_FunctionBody body;
	
	public static class C_FunctionTypeName extends TokenChooser
	{
		public @CHOICE C_Keyword MAIN = new C_Keyword("main");
		
		public @CHOICE static class C_Function_TypeAndName extends TokenSequence
		{
			public C_Type ctype;
			public @OPT TokenList<C_Comment> comments1;
			public C_Function_Definition functionName;
		}
	}
	
	public static class C_Function_ParameterDefs extends TokenSequence
	{
		public @NOSPACE PunctuationLeftParen leftParen;
		public @OPT C_Comment comment1;
		public @OPT @NOSPACE C_FunctionParameter param;
		public @OPT C_Comment comment2;
		public @OPT @NOSPACE TokenList<C_MoreParameterDefs> moreParams;
		public @NOSPACE PunctuationRightParen rightParen;
	}

	public static class C_FunctionParameter extends TokenChooser
	{
		public @FIRST C_FunctionPointer functionPointer;
		
		public @CHOICE static class C_FunctionRegularParameter extends TokenSequence
		{
			public @NOSPACE @OPT C_Keyword CONST = new C_Keyword("const");
			public C_Type ctype;
			public @OPT C_Keyword RESTRICT = new C_Keyword("__restrict");
			public @OPT C_Variable_Definition id;
			public @OPT TokenList<C_Subscript> subscripts;
			public @OPT C_Comment comment;
		}
		
		public @CHOICE static class C_FunctionDotDotDotParameter extends TokenSequence
		{
			public C_Punctuation dotDotDot = new C_Punctuation("...");
		}
	}
		
	public static class C_MoreParameterDefs extends TokenSequence
	{
		public @NOSPACE PunctuationComma comma;
		public @OPT C_Comment comment;
		public C_FunctionParameter param;
	}
	
	public static class C_FunctionBody extends TokenChooser
	{
		public @CHOICE static class C_FunctionNoBody extends TokenSequence
		{
			public @OPT C_FunctionAssembler assembler;
			public @OPT TokenList<C_FunctionAttributes> attributes;
			public PunctuationSemicolon semicolon;
			
			public static class C_FunctionAssembler extends TokenSequence
			{
				public C_Keyword ASM = new C_Keyword("__asm__");
				public PunctuationLeftParen leftParen;
				public C_Literal blank;
				public C_Literal functionName;
				public PunctuationRightParen righttParen;
			}
			
			public static class C_FunctionAttributes extends TokenSequence
			{
				public C_Keyword ATTRIBUTE = new C_Keyword("__attribute__");
				public PunctuationLeftParen leftParen1;
				public PunctuationLeftParen leftParen2;
				public C_FunctionAttribute attrib;
				public @OPT TokenList<C_FunctionMoreAttributes> more;
				public PunctuationRightParen righttParen1;
				public PunctuationRightParen righttParen2;
				
				public static class C_FunctionAttribute extends TokenChooser
				{
					public @CHOICE C_KeywordChoice ATTR = new C_KeywordChoice(
							"__const__",
							"__deprecated__",
							"__leaf__",
							"__malloc__",
							"__noreturn__",
							"__nothrow__",
							"__pure__",
							"__warn_unused_result__"
					);
					
					public @CHOICE static class C_FunctionAttributeFormat extends TokenSequence
					{
						public C_Keyword FORMAT = new C_Keyword("__format__");
						public PunctuationLeftParen leftParen;
						public C_KeywordChoice FMTS = new C_KeywordChoice("__printf__", "__scanf__", "__strfmon__");
						public PunctuationComma comma1;
						public C_Number number1;
						public PunctuationComma comma2;
						public C_Number number2;
						public PunctuationRightParen righttParen;
					}
					
					public @CHOICE static class C_FunctionAttributeNonNull extends TokenSequence
					{
						public C_Keyword NONNULL = new C_Keyword("__nonnull__");
						public PunctuationLeftParen leftParen;
						public SeparatedList<C_Number, PunctuationComma> numbers;
						public PunctuationRightParen righttParen;
					}

				
					public @CHOICE static class C_FunctionAttributeAllocSize extends TokenSequence
					{
						public C_Keyword NONNULL = new C_Keyword("__alloc_size__");
						public PunctuationLeftParen leftParen;
						public SeparatedList<C_Number, PunctuationComma> numbers;
						public PunctuationRightParen righttParen;
					}
				}
				
				public static class C_FunctionMoreAttributes extends TokenSequence
				{
					public PunctuationComma comma;
					public C_FunctionAttribute attrib;
				}
			}
		}
		
		public @CHOICE static class C_FunctionImplementation extends TokenSequence
		{
			public @INDENT PunctuationLeftBrace leftBrace;
			public @OPT TokenList<C_StatementOrComment> elements;
			public @OUTDENT PunctuationRightBrace rightBrace;
			public @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;
		}
	}
}
