// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2026

package com.eagle.programmar.Lisp.Specials;

import com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
import com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Lisp_QuoteOperator extends PrimaryOperator
{
	public @S(10) Lisp_Punctuation quote = new Lisp_Punctuation('\'');
	public @S(20) Lisp_PunctuationChoice operator = new Lisp_PunctuationChoice(
			"<", "<=", "=", ">=", ">", "+", "-", "*", "/");
}
