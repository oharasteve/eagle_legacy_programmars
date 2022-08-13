// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.Lisp.Loops;

import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class Lisp_LoopTerminationTest extends TokenSequence
{
	public @S(10) Lisp_KeywordChoice when = new Lisp_KeywordChoice(
			"while", "until", "repeat", "always", "never", "thereis");
	public @S(20) Lisp_SExpr condition;
}