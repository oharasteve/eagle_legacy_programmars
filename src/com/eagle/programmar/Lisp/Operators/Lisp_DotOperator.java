// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 16, 2026

package com.eagle.programmar.Lisp.Operators;

import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Lisp_DotOperator extends PrecedenceOperator
{
	public @S(10) Lisp_Expression left = new Lisp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Lisp_Punctuation dot = new Lisp_Punctuation('.');
	public @S(30) Lisp_Expression right = new Lisp_Expression(this, AllowedPrecedence.HIGHER);
}
