// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 15, 2013

package com.eagle.programmar.Lisp.Functions;

import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Symbols.Lisp_Function_Definition;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_DefmacroFunction extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @DOC("m_defmac.htm") Lisp_Keyword DEFMACRO = new Lisp_Keyword("defmacro");
	public @S(30) Lisp_Function_Definition name;
	public @S(40) Lisp_Expression arguments;
	public @S(50) TokenList<Lisp_Expression> body;
	public @S(60) PunctuationRightParen rightParen;
}
