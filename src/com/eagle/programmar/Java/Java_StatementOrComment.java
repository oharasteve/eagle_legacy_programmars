// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.tokens.TokenChooser;

public class Java_StatementOrComment extends TokenChooser
{
	public @FIRST @NEWLINE Java_Comment XXcomment;
	public @CHOICE @NEWLINE Java_Statement XXstatement;
}