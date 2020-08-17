// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_HashNumber;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber.JavaP_HexNoPrefix;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_LClassName;
import com.eagle.programmar.JavaP.Terminals.JavaP_Literal;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class JavaP_RuntimeVisibleAnnotations extends TokenSequence
{
	public JavaP_Keyword ANNOTATIONS = new JavaP_Keyword("RuntimeVisibleAnnotations");
	public PunctuationColon colon;
	public JavaP_RuntimeVisibleAnnotationChoice choice;
	
	public static class JavaP_RuntimeVisibleAnnotationChoice extends TokenChooser
	{
		public @CHOICE static class JavaP_RuntimeVisibleAnnotationLength extends TokenSequence
		{
			public JavaP_Keyword LENGTH = new JavaP_Keyword("length");
			public PunctuationEquals equals;
			public JavaP_HexNumber hex;
			public TokenList<JavaP_AnnotHexes> hexes;
			
			public static class JavaP_AnnotHexes extends TokenChooser
			{
				public @CHOICE JavaP_HexNoPrefix hex;
				public @CHOICE JavaP_EndOfLine eoln;
			}
		}
		
		public @CHOICE static class JavaP_RuntimeVisibleAnnotationNoLength extends TokenSequence
		{
			public JavaP_EndOfLine eoln;
			public TokenList<JavaP_RuntimeAnnotation> annotations;

			public static class JavaP_RuntimeAnnotation extends TokenSequence
			{
				public JavaP_Number seq;
				public PunctuationColon colon;
				public JavaP_HashNumber id;
				public PunctuationLeftParen leftParen;
				public @OPT JavaP_RuntimeAnnotationValue value;
				public PunctuationRightParen rightParen;
				public JavaP_EndOfLine eoln1;
				public @OPT JavaP_QualifiedName name;
				public @OPT JavaP_AnnotationValue annotationValue;
				public @OPT JavaP_EndOfLine eoln2;
				
				public static class JavaP_RuntimeAnnotationValue extends TokenSequence
				{
					public JavaP_HashNumber id;
					public PunctuationEquals equals;
					public JavaP_WhichAnnotation which;
				}

				public static class JavaP_AnnotationIdentifier extends TokenSequence
				{
					public JavaP_KeywordChoice CEIS = new JavaP_KeywordChoice("c", "e", "I", "s");
					public JavaP_HashNumber id;
					public @OPT PunctuationPeriod dot;
					public @OPT JavaP_HashNumber id4;
				}
		
				public static class JavaP_WhichAnnotation extends TokenChooser
				{
					public @CHOICE JavaP_AnnotationIdentifier id;
		
					public @CHOICE static class JavaP_AnnotationList extends TokenSequence
					{
						public PunctuationLeftBracket leftBracket;
						public SeparatedList<JavaP_AnnotationIdentifier,PunctuationComma> idList;
						public PunctuationRightBracket rightBracket;
					}
				}
				
				public static class JavaP_AnnotationValue extends TokenSequence
				{
					public PunctuationLeftParen leftParen;
					public JavaP_EndOfLine eoln1;
					public JavaP_KeywordChoice VALUE = new JavaP_KeywordChoice("value", "name");
					public PunctuationEquals equals;
					public JavaP_AnnotationValueClass valueClass;
					public JavaP_EndOfLine eoln2;
					public PunctuationRightParen rightParen;
					public JavaP_EndOfLine eoln3;
					
					public static class JavaP_AnnotationValueClass extends TokenChooser
					{
						public @CHOICE JavaP_Literal literal;
						public @CHOICE JavaP_Number number;
						
						public static @CHOICE class JavaP_AnnotationValueOneClass extends TokenSequence
						{
							public JavaP_Keyword CLASS = new JavaP_Keyword("class");
							public JavaP_LClassName className;
							public PunctuationSemicolon semicolon;
						}
						
						public static @CHOICE class JavaP_AnnotationValueRuntimeClass extends TokenSequence
						{
							public JavaP_LClassName className;
							public PunctuationSemicolon semicolon;
							public PunctuationPeriod dot;
							public JavaP_Keyword RUNTIME = new JavaP_Keyword("RUNTIME");
						}
						
						public static @CHOICE class JavaP_AnnotationValueManyClasses extends TokenSequence
						{
							public PunctuationLeftBracket leftBracket;
							public SeparatedList<JavaP_AnnotationValueOneClass, PunctuationComma> classNames;
							public PunctuationRightBracket rightBracket;
						}						
					}
				}
			}
		}
	}
}
