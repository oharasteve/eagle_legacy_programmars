// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.tokens.TokenSequence;

public class Delphi_Procedure_Call extends TokenSequence
{
	public @S(10) Delphi_Expression expr;
}
