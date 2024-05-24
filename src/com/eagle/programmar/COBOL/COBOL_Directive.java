// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Terminals.COBOL_Identifier;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class COBOL_Directive extends TokenSequence
{
	public @S(10) COBOL_Punctuation dollar = new COBOL_Punctuation('$');
	public @S(20) COBOL_WhichDirective which;

	public static class COBOL_WhichDirective extends TokenChooser
	{
		public @CHOICE COBOL_Keyword END = new COBOL_Keyword("END");

		public @CHOICE static class COBOL_IfDefinedDirective extends TokenSequence
		{
			public @S(10) COBOL_Keyword IF = new COBOL_Keyword("IF");
			public @S(20) COBOL_Identifier var;
			public @S(30) COBOL_Keyword NOT = new COBOL_Keyword("NOT");
			public @S(40) COBOL_Keyword DEFINED = new COBOL_Keyword("DEFINED");
		}

		public @LAST static class COBOL_IfExprDirective extends TokenSequence
		{
			public @S(10) COBOL_Keyword IF = new COBOL_Keyword("IF");
			public @S(20) COBOL_Expression expr;
		}

		public @CHOICE static class COBOL_SetDirective extends TokenSequence
		{
			public @S(10) COBOL_Keyword SET = new COBOL_Keyword("SET");
			public @S(20) TokenList<COBOL_SetWhat> sets;

			public static class COBOL_SetWhat extends TokenChooser
			{
				public @CHOICE COBOL_SetString setString;
				public @CHOICE COBOL_SetParensPlus setParensPlus;
				public @CHOICE COBOL_SetParensNumber setParensNumber;
			}

			public static class COBOL_SetString extends TokenSequence
			{
				public @S(10) COBOL_KeywordChoice key = new COBOL_KeywordChoice("ANS85", "CASE", "CONSTANT", "DATALIT",
						"DEFAULTBYTE", "DIALECT", "ISO2002", "KEYCOMPRESS", "LINKCOUNT", "MF", "MFOO", "NESTCALL",
						"NOALTER", "NOKEYCOMPRESS", "NOOSVS", "NOQUAL", "NOVSC2", "QUAL", "SOURCEFORMAT");
				public @S(20) @OPT COBOL_Literal val;
			}

			public static class COBOL_SetParensPlus extends TokenSequence
			{
				public @S(10) COBOL_KeywordChoice key = new COBOL_KeywordChoice("OOCTRL", "PREPROCESS", "REMOVE");
				public @S(20) PunctuationLeftParen leftParen;
				public @S(30) @OPT COBOL_Punctuation plus = new COBOL_Punctuation('+');
				public @S(40) COBOL_KeywordChoice code = new COBOL_KeywordChoice("CONTROL", "P", "WINDOW1");
				public @S(50) PunctuationRightParen rightParen;
			}

			public static class COBOL_SetParensNumber extends TokenSequence
			{
				public @S(10) COBOL_KeywordChoice key = new COBOL_KeywordChoice("INTCODE");
				public @S(20) PunctuationLeftParen leftParen;
				public @S(30) COBOL_Number number;
				public @S(40) PunctuationRightParen rightParen;
			}
		}
	}
}
