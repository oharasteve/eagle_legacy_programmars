// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 27, 2024

package com.eagle.programmar.Lisp.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Lisp.Lisp_Format;
import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_FormatFunction extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @DOC("s_format.htm") Lisp_Keyword FORMAT = new Lisp_Keyword("format");
	public @S(30) Lisp_Keyword T = new Lisp_Keyword("T");
	public @S(40) TokenList<Lisp_SExpr> items;
	public @S(50) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String txt = Lisp_Format.format(interpreter, items);
		System.out.println(txt);
	}
}
