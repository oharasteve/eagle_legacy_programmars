// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.tokens.TokenChooser;

public class Perl_StatementOrComment extends TokenChooser
{
	public @CHOICE Perl_Statement XXstatement;
	public @CHOICE Perl_Comment XXcomment;
}