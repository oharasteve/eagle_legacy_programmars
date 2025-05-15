// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.Lisp.Loops;

import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenSequence;

public class Lisp_LoopUnconditionalReturn extends TokenSequence
{
	public @S(10) Lisp_Keyword RETURN = new Lisp_Keyword("return");
	public @S(20) Lisp_Expression value;
}