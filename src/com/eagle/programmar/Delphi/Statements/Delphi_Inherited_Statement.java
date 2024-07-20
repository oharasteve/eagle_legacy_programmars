// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Argument_List;
import com.eagle.programmar.Delphi.Delphi_Variable;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;

public class Delphi_Inherited_Statement extends TokenSequence
{
	public @S(10) Delphi_Keyword INHERITED = new Delphi_Keyword("Inherited");
	public @S(20) @OPT Delphi_Variable name;
	public @S(30) @OPT Delphi_Argument_List params;
}
