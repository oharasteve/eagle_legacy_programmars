// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2026

package com.eagle.programmar.Lisp.Specials;

import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Lisp_Colon extends PrimaryOperator
{
	public @S(10) PunctuationColon colon;
	public @S(20) Lisp_Expression expr;
}
