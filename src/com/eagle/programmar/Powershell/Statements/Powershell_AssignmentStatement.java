// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 13, 2015

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Powershell_AssignmentStatement extends TokenSequence
{
	public @NEWLINE Powershell_Variable var;
	public PunctuationEquals equals;
	public Powershell_Expression expr;
	public @NOSPACE PunctuationSemicolon semicolon;
}
