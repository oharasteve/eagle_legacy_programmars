// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Delphi_Halt_Statement extends TokenSequence implements AbstractStatement
{
	public @S(10) Delphi_Keyword HALT = new Delphi_Keyword("Halt");
}
