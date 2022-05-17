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
import com.eagle.tokens.punctuation.PunctuationAmpersand;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_Function extends TokenSequence
{
	public @S(5) @OPT C_Extern_C externC;
	public @S(10) @OPT C_Keyword EXTENSION = new C_Keyword("__extension__");
	public @S(20) @OPT C_KeywordChoice scope1 = new C_KeywordChoice(C_Program.getModifiers());
	public @S(30) @OPT C_Comment comment1;
	public @S(40) @OPT C_KeywordChoice scope2 = new C_KeywordChoice(C_Program.getModifiers());
	public @S(50) C_FunctionTypeName typeName;
	public @S(60) C_Function_ParameterDefs parameters;
	public @S(70) @OPT TokenList<C_Comment> comments2;
	public @S(80) @OPT C_Keyword CONST = new C_Keyword("const");
	public @S(90) C_FunctionBody body;
	
	public static class C_FunctionTypeName extends TokenChooser
	{
		public @CHOICE C_Keyword MAIN = new C_Keyword("main");   // Strange syntax with no return type on 'main'
		
		public @CHOICE static class C_Function_TypeAndName extends TokenSequence
		{
			public @S(10) C_Type ctype;
			public @S(20) @OPT TokenList<C_Comment> comments1;
			public @S(30) C_Function_Definition functionName;
		}
	}
	
	public static class C_Function_ParameterDefs extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationLeftParen leftParen;
		public @S(20) @OPT C_Comment comment1;
		public @S(30) @OPT @NOSPACE C_FunctionParameter param;
		public @S(40) @OPT C_Comment comment2;
		public @S(50) @OPT @NOSPACE TokenList<C_MoreParameterDefs> moreParams;
		public @S(60) @NOSPACE PunctuationRightParen rightParen;
	}

	public static class C_FunctionParameter extends TokenChooser
	{
		public @FIRST C_FunctionPointer functionPointer;
		
		public @CHOICE static class C_FunctionParamAmpersand extends TokenSequence
		{
			public @S(10) PunctuationAmpersand ampersand;
			public @S(20) C_Type type;
		}
		
		public @CHOICE static class C_FunctionRegularParameter extends TokenSequence
		{
			public @S(10) @NOSPACE @OPT C_Keyword CONST = new C_Keyword("const");
			public @S(20) C_Type ctype;
			public @S(30) @OPT C_Keyword RESTRICT = new C_Keyword("__restrict");
			public @S(40) @OPT C_Variable_Definition id;
			public @S(50) @OPT TokenList<C_Subscript> subscripts;
			public @S(60) @OPT C_FunctionDefaultValue value;
			public @S(70) @OPT C_Comment comment;
			
			public static class C_FunctionDefaultValue extends TokenSequence
			{
				public @S(10) PunctuationEquals equals;
				public @S(20) C_Expression expr;
			}
		}
		
		public @CHOICE static class C_FunctionFunctionParameter extends TokenSequence
		{
			public @S(10) C_Type ctype;
			public @S(20) C_Function_Definition id;
			public @S(30) C_Function_ParameterDefs params;
		}
		
		public @CHOICE static class C_FunctionDotDotDotParameter extends TokenSequence
		{
			public @S(10) C_Punctuation dotDotDot = new C_Punctuation("...");
		}
	}
		
	public static class C_MoreParameterDefs extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationComma comma;
		public @S(20) @OPT C_Comment comment;
		public @S(30) C_FunctionParameter param;
	}
	
	public static class C_FunctionBody extends TokenChooser
	{
		public @CHOICE static class C_FunctionEqualsZero extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) C_Number zero;
		}
		
		public @CHOICE static class C_FunctionEqualsDefault extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) C_Keyword DEFAULT = new C_Keyword("default");
		}
		
		public @CHOICE static class C_FunctionNoBody extends TokenSequence
		{
			public @S(10) @OPT C_FunctionAssembler assembler;
			public @S(20) @OPT TokenList<C_FunctionAttributes> attributes;
			public @S(30) PunctuationSemicolon semicolon;
			
			public static class C_FunctionAssembler extends TokenSequence
			{
				public @S(10) C_Keyword ASM = new C_Keyword("__asm__");
				public @S(20) PunctuationLeftParen leftParen;
				public @S(30) C_Literal blank;
				public @S(40) C_Literal functionName;
				public @S(50) PunctuationRightParen righttParen;
			}
			
			public static class C_FunctionAttributes extends TokenSequence
			{
				public @S(10) C_Keyword ATTRIBUTE = new C_Keyword("__attribute__");
				public @S(20) PunctuationLeftParen leftParen1;
				public @S(30) PunctuationLeftParen leftParen2;
				public @S(40) C_FunctionAttribute attrib;
				public @S(50) @OPT TokenList<C_FunctionMoreAttributes> more;
				public @S(60) PunctuationRightParen righttParen1;
				public @S(70) PunctuationRightParen righttParen2;
				
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
						public @S(10) C_Keyword FORMAT = new C_Keyword("__format__");
						public @S(20) PunctuationLeftParen leftParen;
						public @S(30) C_KeywordChoice FMTS = new C_KeywordChoice("__printf__", "__scanf__", "__strfmon__");
						public @S(40) PunctuationComma comma1;
						public @S(50) C_Number number1;
						public @S(60) PunctuationComma comma2;
						public @S(70) C_Number number2;
						public @S(80) PunctuationRightParen righttParen;
					}
					
					public @CHOICE static class C_FunctionAttributeNonNull extends TokenSequence
					{
						public @S(10) C_Keyword NONNULL = new C_Keyword("__nonnull__");
						public @S(20) PunctuationLeftParen leftParen;
						public @S(30) SeparatedList<C_Number, PunctuationComma> numbers;
						public @S(40) PunctuationRightParen righttParen;
					}

				
					public @CHOICE static class C_FunctionAttributeAllocSize extends TokenSequence
					{
						public @S(10) C_Keyword NONNULL = new C_Keyword("__alloc_size__");
						public @S(20) PunctuationLeftParen leftParen;
						public @S(30) SeparatedList<C_Number, PunctuationComma> numbers;
						public @S(40) PunctuationRightParen righttParen;
					}
				}
				
				public static class C_FunctionMoreAttributes extends TokenSequence
				{
					public @S(10) PunctuationComma comma;
					public @S(20) C_FunctionAttribute attrib;
				}
			}
		}
		
		public @CHOICE static class C_FunctionImplementation extends TokenSequence
		{
			public @S(10) @INDENT PunctuationLeftBrace leftBrace;
			public @S(20) @OPT TokenList<C_StatementOrComment> elements;
			public @S(30) @OUTDENT PunctuationRightBrace rightBrace;
			public @S(40) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;
		}
	}
}
