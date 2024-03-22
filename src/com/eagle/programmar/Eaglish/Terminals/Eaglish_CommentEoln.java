// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.Eaglish.Terminals;

import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.tokens.TokenSequence;

public class Eaglish_CommentEoln extends TokenSequence
{
	public @S(10) Python_Comment comment;
	public @S(20) @OPT Python_EndOfLine eoln;
}