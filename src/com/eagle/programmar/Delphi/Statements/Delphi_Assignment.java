// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Variable;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Delphi_Assignment extends TokenSequence
{
	public @S(10) Delphi_Variable var;
	public @S(20) Delphi_Punctuation colonEquals = new Delphi_Punctuation(":=");
	public @S(30) Delphi_Expression expr;
	public @S(40) @OPT Delphi_Comment comment;
}
