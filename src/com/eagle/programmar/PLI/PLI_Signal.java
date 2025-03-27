// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 19, 2012

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class PLI_Signal extends TokenChooser
{
	public @CHOICE PLI_KeywordChoice XXwhich = new PLI_KeywordChoice(PLI_Signals.signals);

	public @CHOICE static class PLI_OnEndFile extends TokenSequence
	{
		public @S(10) PLI_Keyword ENDFILE = new PLI_Keyword("ENDFILE");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) PLI_Identifier_Reference file;
		public @S(40) PunctuationRightParen rightParen;
	}
}
