// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;

public class Delphi_Raise_Statement extends TokenSequence
{
	public @S(10) @DOC("Exceptions_(Delphi)#Raising_and_Handling_Exceptions") Delphi_Keyword RAISE = new Delphi_Keyword("Raise");
	public @S(20) Delphi_Expression exception;
}
