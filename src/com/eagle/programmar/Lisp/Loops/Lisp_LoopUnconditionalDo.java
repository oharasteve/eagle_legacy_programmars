// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.Lisp.Loops;

import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Lisp_LoopUnconditionalDo extends TokenSequence
{
	public @S(10) Lisp_KeywordChoice DO = new Lisp_KeywordChoice("do", "doing");
	public @S(20) TokenList<Lisp_Expression> actions;
}