// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 27, 2024

package com.eagle.programmar.Lisp;

import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Lisp_Type extends TokenSequence
{
	public @S(10) Lisp_Punctuation QUOTE = new Lisp_Punctuation("'");
	public @S(20) Lisp_KeywordChoice STRING = new Lisp_KeywordChoice("STRING");
}
