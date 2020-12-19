// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 18, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Signal;
import com.eagle.programmar.PLI.PLI_Signals;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_SignalStatement extends TokenSequence
{
	public @S(10) @OPT PLI_Signal_Label label;
	public @S(20) @DOC("7.52") PLI_Keyword SIGNAL = new PLI_Keyword("SIGNAL");
	public @S(30) PLI_Signal signal;
	public @S(40) PunctuationSemicolon semicolon;

	public static class PLI_Signal_Label extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) PLI_KeywordChoice which = new PLI_KeywordChoice(PLI_Signals.signals);
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) PunctuationColon colon;
	}
}
