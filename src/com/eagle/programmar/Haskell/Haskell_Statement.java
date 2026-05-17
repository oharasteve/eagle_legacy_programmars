// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 12, 2026

package com.eagle.programmar.Haskell;

import com.eagle.programmar.Haskell.Statements.Haskell_CommentStatement;
import com.eagle.programmar.Haskell.Statements.Haskell_Function;
import com.eagle.tokens.TokenChooser;

public class Haskell_Statement extends TokenChooser
{
	public @CHOICE Haskell_CommentStatement XXcomment;
	public @CHOICE Haskell_Function XXfunction;
}
