// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.Lisp.Loops;

import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class Lisp_LoopListAccumulation extends TokenSequence
{
	public @S(10) Lisp_KeywordChoice operation = new Lisp_KeywordChoice("append", "appenging", "collect", "collecting",
			"nconc", "nconcing");
	public @S(20) Lisp_Expression expr;
}