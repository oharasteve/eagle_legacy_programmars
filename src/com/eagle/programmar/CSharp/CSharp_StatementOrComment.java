// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.tokens.TokenChooser;

public class CSharp_StatementOrComment extends TokenChooser
{
	public @CHOICE @NEWLINE CSharp_Statement statement;
	public @CHOICE @NEWLINE CSharp_Comment comment;
}
