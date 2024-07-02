// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 19, 2012

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.PLI_Signal;
import com.eagle.programmar.PLI.Statements.PLI_SignalStatement.PLI_Signal_Label;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_RevertStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @OPT PLI_Label label1;
	public @S(20) @OPT PLI_Signal_Label label2;
	public @S(30) PLI_Keyword REVERT = new PLI_Keyword("REVERT");
	public @S(40) PLI_Signal signal;
	public @S(50) PunctuationSemicolon semicolon;
}
