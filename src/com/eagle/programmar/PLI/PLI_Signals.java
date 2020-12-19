// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 3, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class PLI_Signals extends TokenSequence
{
	public static String signals[] = new String[] {
		"CONVERSION",
		"ERROR",
		"FIXEDOVERFLOW",
		"NOFIXEDOVERFLOW",
		"NOFOFL",
		"NOSIZE",
		"NOSTRINGSIZE",
		"OVERFLOW",
		"SIZE",
		"STRINGRANGE",
		"STRINGSIZE",
		"SUBSCRIPTRANGE",
		"UNDERFLOW",
		"ZERODIVIDE"
		};

	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) PLI_KeywordChoice which = new PLI_KeywordChoice(signals);
	public @S(30) @OPT TokenList<PLI_Procedure_MoreSignals> moreSignals;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationColon colon;
	
	public static class PLI_Procedure_MoreSignals extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) PLI_KeywordChoice which = new PLI_KeywordChoice(signals);
	}
}
