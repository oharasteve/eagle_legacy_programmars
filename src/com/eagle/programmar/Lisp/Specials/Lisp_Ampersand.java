// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2026

package com.eagle.programmar.Lisp.Specials;

import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Lisp_Ampersand extends PrimaryOperator
{
	public @S(10) Lisp_Punctuation ampersand = new Lisp_Punctuation('&');
	public @S(20) Lisp_Expression expr;
}
