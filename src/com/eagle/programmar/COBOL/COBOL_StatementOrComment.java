// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.tokens.TokenChooser;

public class COBOL_StatementOrComment extends TokenChooser
{
	public @CHOICE COBOL_Comment comment;
	public @CHOICE COBOL_Statement statement;
}