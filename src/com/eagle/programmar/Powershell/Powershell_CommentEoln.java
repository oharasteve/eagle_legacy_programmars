// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 20, 2022

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Terminals.Powershell_Comment;
import com.eagle.tokens.TokenSequence;

public class Powershell_CommentEoln extends TokenSequence
{
	public @S(10) Powershell_Comment comment;
	public @S(20) @OPT Powershell_EndOfLine eoln;
}