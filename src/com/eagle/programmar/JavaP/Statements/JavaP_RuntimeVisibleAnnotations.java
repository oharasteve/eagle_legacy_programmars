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

// We use 'Anno' here to mean 'Annotation' and 'Viz' for 'Visible' and 'RT' for 'Runtime'
// Class names were getting crazy long for inner inner inner inner inner inner inner classes

public class JavaP_RuntimeVisibleAnnotations extends TokenSequence
{
	public JavaP_Keyword ANNOTATIONS = new JavaP_Keyword("RuntimeVisibleAnnotations");
	public PunctuationColon colon;
	public JavaP_RTVizAnnoChoice choice;
	
	public static class JavaP_RTVizAnnoChoice extends TokenChooser
	{
		public @CHOICE static class JavaP_RTVizAnnoLength extends TokenSequence
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
		
		public @CHOICE static class JavaP_RTVizAnnoNoLength extends TokenSequence
		{
			public JavaP_EndOfLine eoln;
			public TokenList<JavaP_RTAnno> annotations;

			public static class JavaP_RTAnno extends TokenSequence
			{
				public JavaP_Number seq;
				public PunctuationColon colon;
				public JavaP_HashNumber id;
				public PunctuationLeftParen leftParen;
				public @OPT JavaP_RTAnnoValue value;
				public PunctuationRightParen rightParen;
				public JavaP_EndOfLine eoln1;
				public @OPT JavaP_QualifiedName name;
				public @OPT JavaP_AnnoValue annotationValue;
				public @OPT JavaP_EndOfLine eoln2;
				
				public static class JavaP_RTAnnoValue extends TokenSequence
				{
					public JavaP_HashNumber id;
					public PunctuationEquals equals;
					public JavaP_WhichAnno which;
				}

				public static class JavaP_AnnoIdentifier extends TokenSequence
				{
					public JavaP_KeywordChoice CEIS = new JavaP_KeywordChoice("c", "e", "I", "s");
					public JavaP_HashNumber id;
					public @OPT PunctuationPeriod dot;
					public @OPT JavaP_HashNumber id4;
				}
		
				public static class JavaP_WhichAnno extends TokenChooser
				{
					public @CHOICE JavaP_AnnoIdentifier id;
		
					public @CHOICE static class JavaP_AnnoList extends TokenSequence
					{
						public PunctuationLeftBracket leftBracket;
						public SeparatedList<JavaP_AnnoIdentifier,PunctuationComma> idList;
						public PunctuationRightBracket rightBracket;
					}
				}
				
				public static class JavaP_AnnoValue extends TokenSequence
				{
					public PunctuationLeftParen leftParen;
					public JavaP_EndOfLine eoln1;
					public JavaP_KeywordChoice VALUE = new JavaP_KeywordChoice("value", "name");
					public PunctuationEquals equals;
					public JavaP_AnnoValueClass valueClass;
					public JavaP_EndOfLine eoln2;
					public PunctuationRightParen rightParen;
					public JavaP_EndOfLine eoln3;
					
					public static class JavaP_AnnoValueClass extends TokenChooser
					{
						public @CHOICE JavaP_Literal literal;
						public @CHOICE JavaP_Number number;
						
						public static @CHOICE class JavaP_AnnoValueOneClass extends TokenSequence
						{
							public JavaP_Keyword CLASS = new JavaP_Keyword("class");
							public JavaP_LClassName className;
							public PunctuationSemicolon semicolon;
						}
						
						public static @CHOICE class JavaP_AnnoValueRTClass extends TokenSequence
						{
							public JavaP_LClassName className;
							public PunctuationSemicolon semicolon;
							public PunctuationPeriod dot;
							public JavaP_Keyword RT = new JavaP_Keyword("RUNTIME");
						}
						
						public static @CHOICE class JavaP_AnnoValueManyClasses extends TokenSequence
						{
							public PunctuationLeftBracket leftBracket;
							public SeparatedList<JavaP_AnnoValueOneClass, PunctuationComma> classNames;
							public PunctuationRightBracket rightBracket;
						}						
					}
				}
			}
		}
	}
}
