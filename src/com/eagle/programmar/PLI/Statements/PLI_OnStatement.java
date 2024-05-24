// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.PLI_Signal;
import com.eagle.programmar.PLI.PLI_Statement;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_OnStatement extends TokenSequence
{
	public @S(10) @OPT PLI_Label label;
	public @S(20) @DOC("7.36") PLI_Keyword ON = new PLI_Keyword("ON");
	public @S(30) SeparatedList<PLI_Signal, PunctuationComma> signals;
	public @S(40) @OPT PLI_Keyword SNAP = new PLI_Keyword("SNAP");
	public @S(50) PLI_OnAction action;

	public static class PLI_OnAction extends TokenChooser
	{
		public @CHOICE PLI_Statement stmt;

		public @CHOICE static class PLI_On_Action_System extends TokenSequence
		{
			public @S(10) PLI_Keyword SYSTEM = new PLI_Keyword("SYSTEM");
			public @S(20) PunctuationSemicolon semicolon;
		}
	}
}
