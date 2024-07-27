// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2024

package com.eagle.programmar.Lisp;

import com.eagle.programmar.Lisp.Terminals.Lisp_Comment;
import com.eagle.tokens.TokenChooser;

public class Lisp_SExprOrComment extends TokenChooser
{
	public @CHOICE Lisp_SExpr XXexpr;
	public @CHOICE Lisp_Comment XXcomment;
}
