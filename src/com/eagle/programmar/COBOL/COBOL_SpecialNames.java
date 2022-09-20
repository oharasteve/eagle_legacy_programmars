// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_SpecialNames extends TokenSequence
{
	public @S(10) COBOL_Keyword SPECIALNAMES = new COBOL_Keyword("SPECIAL-NAMES");
	public @S(20) PunctuationPeriod dot1;
	public @S(30) TokenList<COBOL_SpecialName> specialNames;
	public @S(40) @OPT PunctuationPeriod dot2;

	public static class COBOL_SpecialName extends TokenChooser
	{
		public @CHOICE static class COBOL_SpecialNameCursor extends TokenSequence
		{
			public @S(10) COBOL_Keyword CURSOR = new COBOL_Keyword("CURSOR");
			public @S(20) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(30) COBOL_Identifier_Reference var;
		}

		public @CHOICE static class COBOL_SpecialNameCurrency extends TokenSequence
		{
			public @S(10) COBOL_Keyword CURRENCY = new COBOL_Keyword("CURRENCY");
			public @S(20) COBOL_Keyword SIGN = new COBOL_Keyword("SIGN");
			public @S(30) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(40) COBOL_Literal sign;
		}

		public @CHOICE static class COBOL_SpecialNameClass extends TokenSequence
		{
			public @S(10) COBOL_Keyword CLASS = new COBOL_Keyword("CLASS");
			public @S(20) COBOL_Identifier_Reference name;
			public @S(30) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(40) SeparatedList<COBOL_SpecialNameClassValue,PunctuationComma> values;
			
			public static class COBOL_SpecialNameClassValue extends TokenSequence
			{
				public @S(10) COBOL_Expression value1;
				public @S(20) @OPT COBOL_Keyword THRU = new COBOL_Keyword("THRU");
				public @S(30) @OPT COBOL_Expression value2;
			}
		}

		public @CHOICE static class COBOL_SpecialNameConsole extends TokenSequence
		{
			public @S(10) COBOL_Keyword CONSOLE = new COBOL_Keyword("CONSOLE");
			public @S(20) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(30) COBOL_Keyword CRT = new COBOL_Keyword("CRT");
		}

		public @CHOICE static class COBOL_SpecialNameCrtStatus extends TokenSequence
		{
			public @S(10) COBOL_Keyword CRT = new COBOL_Keyword("CRT");
			public @S(20) COBOL_Keyword STATUS = new COBOL_Keyword("STATUS");
			public @S(30) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(40) COBOL_Identifier_Reference var;
		}

		public @CHOICE static class COBOL_SpecialNameCallConvention extends TokenSequence
		{
			public @S(10) COBOL_Keyword CALL_CONVENTION = new COBOL_Keyword("CALL-CONVENTION");
			public @S(20) COBOL_Number number;
			public @S(30) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(40) COBOL_KeywordChoice convention = new COBOL_KeywordChoice("STATICCOBOL", "STATICW32API", "WINAPI");
		}
	}
}
