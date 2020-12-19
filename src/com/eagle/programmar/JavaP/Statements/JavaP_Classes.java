// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.JavaP_CodeBlock;
import com.eagle.programmar.JavaP.JavaP_Syntax;
import com.eagle.programmar.JavaP.JavaP_Value;
import com.eagle.programmar.JavaP.Blocks.JavaP_CodeLineNumbers;
import com.eagle.programmar.JavaP.Blocks.JavaP_CodeLocalValues;
import com.eagle.programmar.JavaP.Statements.JavaP_Classes.JavaP_OneClass.JavaP_MethodArgument.JavaP_MethodArg.JavaP_EmptySubscript;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber;
import com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.programmar.JavaP.Terminals.JavaP_RestOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class JavaP_Classes extends TokenSequence
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) JavaP_EndOfLine eoln1;
	public @S(30) @OPT TokenList<JavaP_OneClass> oneClass;
	public @S(40) PunctuationRightBrace rightBrace;
	public @S(50) JavaP_EndOfLine eoln2;
	
	public static class JavaP_OneClass extends TokenSequence
	{
		public @S(10) @OPT TokenList<JavaP_Modifier> modifier;
		public @S(20) JavaP_OneClassHeader header;
		public @S(30) PunctuationSemicolon semicolon;
		public @S(40) JavaP_EndOfLine eoln1;
		public @S(50) TokenList<JavaP_OneClassParameter> parameters;
		public @S(60) @OPT JavaP_EndOfLine eoln2;

		public static class JavaP_OneClassHeader extends TokenChooser
		{
			public @CHOICE static class JavaP_OneClassRegularHeader extends TokenSequence
			{
				public @S(10) JavaP_QualifiedName type;
				public @S(20) @OPT JavaP_OneClassGeneric generic;
				public @S(30) @OPT JavaP_EmptySubscript subscript;
				public @S(40) JavaP_OneClassWhat what;
			}

			public @CHOICE static class JavaP_OneClassStaticHeader extends TokenSequence
			{
				public @S(10) PunctuationLeftBrace leftBrace;
				public @S(20) PunctuationRightBrace rightBrace;
			}
		}

		public static class JavaP_Modifier extends TokenSequence
		{
			public @S(10) JavaP_KeywordChoice PUBLIC = new JavaP_KeywordChoice(
					"abstract",
					"final",
					"private",
					"protected",
					"public",
					"static",
					"synchronized",
					"volatile"
			);
		}
		
		public static class JavaP_OneClassWhat extends TokenChooser
		{
			public @LAST JavaP_QualifiedName data;
			
			public @CHOICE static class JavaP_OneClassMethod extends TokenSequence
			{
				public @S(10) @OPT JavaP_QualifiedName name;
				public @S(20) PunctuationLeftParen leftParen;
				public @S(30) @OPT SeparatedList<JavaP_MethodArgument, PunctuationComma> params;
				public @S(40) PunctuationRightParen rightParen;
				public @S(50) @OPT JavaP_OneClassThrows classThrows;
				
				public static class JavaP_OneClassThrows extends TokenSequence
				{
					public @S(10) JavaP_Keyword THROWS = new JavaP_Keyword("throws");
					public @S(20) SeparatedList<JavaP_QualifiedName,PunctuationComma> name;
				}
			}
		}
		
		public static class JavaP_MethodArgument extends TokenChooser
		{
			public @CHOICE JavaP_Punctuation question = new JavaP_Punctuation('?');
			
			public @CHOICE static class JavaP_MethodArg extends TokenSequence
			{
				public @S(10) @OPT JavaP_QuestionExtends question;
				public @S(20) @OPT JavaP_TypeExtends type;
				public @S(30) JavaP_QualifiedName name;
				public @S(40) @OPT JavaP_OneClassGeneric generic;
				public @S(50) @OPT JavaP_EmptySubscript subscript;
				
				public static class JavaP_QuestionExtends extends TokenSequence
				{
					public @S(10) JavaP_Punctuation question = new JavaP_Punctuation('?');
					public @S(20) JavaP_Keyword EXTENDS = new JavaP_Keyword("extends");
				}
	
				public static class JavaP_TypeExtends extends TokenSequence
				{
					public @S(10) JavaP_Identifier typeName;
					public @S(20) JavaP_Keyword EXTENDS = new JavaP_Keyword("extends");
				}
	
				public static class JavaP_EmptySubscript extends TokenSequence
				{
					public @S(10) PunctuationLeftBracket leftBracket;
					public @S(20) PunctuationRightBracket rightBracket;
				}
			}
		}
		
		public static class JavaP_OneClassGeneric extends TokenSequence
		{
			public @S(10) JavaP_Punctuation lessThan = new JavaP_Punctuation('<');
			public @S(20) SeparatedList<JavaP_MethodArgument,PunctuationComma> names;
			public @S(30) JavaP_Punctuation greaterThan = new JavaP_Punctuation('>');
		}
		
		public static class JavaP_OneClassParameter extends TokenChooser
		{
			public @CHOICE JavaP_CodeBlock code;
			public @CHOICE JavaP_Signature signature;
			public @CHOICE JavaP_RuntimeVisibleAnnotations runtimeAnnotation;
			public @CHOICE JavaP_RuntimeVisibleTypeAnnotations runtimeTypeAnnotation;
			public @CHOICE JavaP_CodeLineNumbers lineNumbers;
			public @CHOICE JavaP_CodeLocalValues localValues;

			public @CHOICE static class JavaP_OneClassDescriptor extends TokenSequence
			{
				public @S(10) JavaP_Keyword DESCRIPTOR = new JavaP_Keyword("descriptor");
				public @S(20) PunctuationColon colon;
				public @S(30) JavaP_Value value;
				public @S(40) JavaP_EndOfLine eoln;
			}
			
			public @CHOICE static class JavaP_OneClassFlags extends TokenSequence
			{
				public @S(10) JavaP_Keyword FLAGS = new JavaP_Keyword("flags");
				public @S(20) PunctuationColon colon;
				public @S(30) @OPT JavaP_OneFlagCode flagCode;
				public @S(40) @OPT SeparatedList<JavaP_OneClassFlag, PunctuationComma> flags;
				public @S(50) JavaP_EndOfLine eoln;
				
				public static class JavaP_OneFlagCode extends TokenSequence
				{
					public @S(10) PunctuationLeftParen leftParen;
					public @S(20) JavaP_HexNumber hex;
					public @S(30) PunctuationRightParen rightParen;
				}

				public static class JavaP_OneClassFlag extends TokenChooser
				{
					public @CHOICE JavaP_KeywordChoice ACC = new JavaP_KeywordChoice(JavaP_Syntax.ACC_CODES);
				}
			}
			
			public @CHOICE static class JavaP_OneClassConstantValue extends TokenSequence
			{
				public @S(10) JavaP_KeywordChoice CONSTANTVALUE = new JavaP_KeywordChoice("Constant", "ConstantValue");
				public @S(20) @OPT JavaP_Keyword VALUE = new JavaP_Keyword("value");
				public @S(30) PunctuationColon colon;
				public @S(40) JavaP_KeywordChoice type = new JavaP_KeywordChoice("int", "long", "String");
				public @S(50) JavaP_RestOfLine value;
				public @S(60) JavaP_EndOfLine eoln;
			}
			
			public @CHOICE static class JavaP_OneClassExceptions extends TokenSequence
			{
				public @S(10) JavaP_Keyword EXCEPTIONS = new JavaP_Keyword("Exceptions");
				public @S(20) PunctuationColon colon1;
				public @S(30) JavaP_EndOfLine eoln1;
				
				public @S(40) JavaP_Keyword THROWS = new JavaP_Keyword("throws");
				public @S(50) SeparatedList<JavaP_QualifiedName,PunctuationComma> name;
				public @S(60) @OPT JavaP_EndOfLine eoln2;
			}
			
			public @CHOICE static class JavaP_OneClassMethodParameters extends TokenSequence
			{
				public @S(10) JavaP_Keyword METHODPARAMETERS = new JavaP_Keyword("MethodParameters");
				public @S(20) PunctuationColon colon;
				public @S(30) JavaP_EndOfLine eoln1;
				
				public @S(40) JavaP_Keyword NAME = new JavaP_Keyword("Name");
				public @S(50) JavaP_Keyword FLAGS = new JavaP_Keyword("Flags");
				public @S(60) JavaP_EndOfLine eoln2;
				
				public @S(70) @OPT TokenList<JavaP_OneClassMethodParameter> params;
				
				public static class JavaP_OneClassMethodParameter extends TokenSequence
				{
					public @S(10) JavaP_QualifiedName name;
					public @S(20) @OPT TokenList<JavaP_Value> values;
					public @S(30) JavaP_EndOfLine eoln;
				}
			}
			
			public @CHOICE static class JavaP_OneClassDeprecated extends TokenSequence
			{
				public @S(10) JavaP_Keyword DEPRECATED = new JavaP_Keyword("Deprecated");
				public @S(20) PunctuationColon colon;
				public @S(30) JavaP_Keyword TRUE = new JavaP_Keyword("true");
				public @S(40) JavaP_EndOfLine eoln;
			}
		}
	}
}
