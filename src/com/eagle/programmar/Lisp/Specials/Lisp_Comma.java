// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2026

package com.eagle.programmar.Lisp.Specials;

import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Lisp_Comma extends PrimaryOperator
{
	public @S(10) PunctuationComma comma;
	public @S(20) @OPT Lisp_Punctuation at = new Lisp_Punctuation('@');
	public @S(30) Lisp_Expression expr;
}
