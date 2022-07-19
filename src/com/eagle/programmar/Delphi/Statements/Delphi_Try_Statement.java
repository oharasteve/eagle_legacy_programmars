// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 16, 2013

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Statement_List;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;

public class Delphi_Try_Statement extends TokenSequence
{
	public @S(10) @DOC("Exceptions_(Delphi)") Delphi_Keyword TRY = new Delphi_Keyword("Try");
	public @S(20) Delphi_Statement_List statements1;
	public @S(30) @OPT Delphi_Keyword EXCEPT = new Delphi_Keyword("Except");
	public @S(40) @OPT Delphi_Statement_List statements2;
	public @S(50) @OPT Delphi_Keyword FINALLY = new Delphi_Keyword("Finally");
	public @S(60) @OPT Delphi_Statement_List statements3;
	public @S(70) Delphi_Keyword END = new Delphi_Keyword("End");
}
