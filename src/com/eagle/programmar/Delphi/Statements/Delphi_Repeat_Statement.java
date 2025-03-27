// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement_List;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Delphi_Repeat_Statement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("Declarations_and_Statements_(Delphi)#Repeat_Statements") Delphi_Keyword REPEAT = new Delphi_Keyword(
			"Repeat");
	public @S(20) Delphi_Statement_List statements;
	public @S(30) Delphi_Keyword UNTIL = new Delphi_Keyword("Until");
	public @S(40) Delphi_Expression expr;
}
