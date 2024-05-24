// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;

public class Delphi_With_Statement extends TokenSequence
{
	public @S(10) @DOC("Declarations_and_Statements_(Delphi)#With_Statements") Delphi_Keyword WITH = new Delphi_Keyword(
			"With");
	public @S(20) Delphi_Expression expr;
	public @S(30) Delphi_Keyword DO = new Delphi_Keyword("Do");
	public @S(40) Delphi_Statement stmt;
}
