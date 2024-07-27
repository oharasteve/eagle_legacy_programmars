// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.JavaP_CodeBlock;
import com.eagle.programmar.JavaP.JavaP_MethodArgument;
import com.eagle.programmar.JavaP.Blocks.JavaP_CodeLineNumbers;
import com.eagle.programmar.JavaP.Blocks.JavaP_CodeLocalValues;
import com.eagle.programmar.JavaP.Parameters.JavaP_OneClassConstantValue;
import com.eagle.programmar.JavaP.Parameters.JavaP_OneClassDeprecated;
import com.eagle.programmar.JavaP.Parameters.JavaP_OneClassDescriptor;
import com.eagle.programmar.JavaP.Parameters.JavaP_OneClassExceptions;
import com.eagle.programmar.JavaP.Parameters.JavaP_OneClassFlags;
import com.eagle.programmar.JavaP.Parameters.JavaP_OneClassGeneric;
import com.eagle.programmar.JavaP.Parameters.JavaP_OneClassMethodParameters;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
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
				public @S(30) @OPT TokenList<JavaP_NoSubscript> subscript;
				public @S(40) JavaP_OneClassWhat what;

				public static class JavaP_NoSubscript extends TokenSequence
				{
					public @S(10) PunctuationLeftBracket leftBracket;
					public @S(20) PunctuationRightBracket rightBracket;
				}
			}

			public @CHOICE static class JavaP_OneClassStaticHeader extends TokenSequence
			{
				public @S(10) PunctuationLeftBrace leftBrace;
				public @S(20) PunctuationRightBrace rightBrace;
			}
		}

		public static class JavaP_Modifier extends TokenSequence
		{
			public @S(10) JavaP_KeywordChoice PUBLIC = new JavaP_KeywordChoice("abstract", "final", "private",
					"protected", "public", "static", "synchronized", "volatile");
		}

		public static class JavaP_OneClassWhat extends TokenChooser
		{
			public @LAST JavaP_QualifiedName XXdata;

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
					public @S(20) SeparatedList<JavaP_QualifiedName, PunctuationComma> name;
				}
			}
		}

		public static class JavaP_OneClassParameter extends TokenChooser
		{
			public @CHOICE JavaP_CodeBlock XXcode;
			public @CHOICE JavaP_Signature XXsignature;
			public @CHOICE JavaP_RuntimeVisibleAnnotations XXruntimeAnnotation;
			public @CHOICE JavaP_RuntimeVisibleTypeAnnotations XXruntimeTypeAnnotation;
			public @CHOICE JavaP_CodeLineNumbers XXlineNumbers;
			public @CHOICE JavaP_CodeLocalValues XXlocalValues;

			public @CHOICE JavaP_OneClassDescriptor XXdescriptor;
			public @CHOICE JavaP_OneClassFlags XXflags;
			public @CHOICE JavaP_OneClassConstantValue XXconstantValue;
			public @CHOICE JavaP_OneClassExceptions XXexceptions;
			public @CHOICE JavaP_OneClassMethodParameters XXmethodParameters;
			public @CHOICE JavaP_OneClassDeprecated XXdeprecated;
		}
	}
}
