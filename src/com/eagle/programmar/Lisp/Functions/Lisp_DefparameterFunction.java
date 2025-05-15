// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.Lisp.Functions;

import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Symbols.Lisp_Parameter_Definition;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_DefparameterFunction extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @DOC("m_defpar.htm") Lisp_Keyword DEFPARAMETER = new Lisp_Keyword("defparameter");
	public @S(30) Lisp_Parameter_Definition name;
	public @S(40) Lisp_Expression value;
	public @S(50) PunctuationRightParen rightParen;
}
