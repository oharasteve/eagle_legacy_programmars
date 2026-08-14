// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2026

package com.eagle.programmar.Lisp.Specials;

import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Lisp_CharString extends PrimaryOperator
{
	public @S(10) Lisp_KeywordChoice charString = new Lisp_KeywordChoice("char", "string");
	public @S(20) @OPT Lisp_Punctuation not = new Lisp_Punctuation('/');
	public @S(30) @OPT Lisp_Punctuation less = new Lisp_Punctuation('<');
	public @S(40) @OPT Lisp_Punctuation greater = new Lisp_Punctuation('>');
	public @S(50) @OPT PunctuationEquals equals;
}
