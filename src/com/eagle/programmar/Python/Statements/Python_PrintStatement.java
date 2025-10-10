// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_PrintStatement extends TokenSequence
		implements AbstractStatement
{
	public @S(10) @NOSPACE Python_Keyword PRINT = new Python_Keyword("print");
	public @S(20) @OPT Python_Punctuation greaterGreater = new Python_Punctuation(">>");
	public @S(30) @OPT SeparatedList<Python_Expression, PunctuationComma> exprs;
	public @S(40) @OPT @NOSPACE @CURIOUS("Extra comma") PunctuationComma comma;
}
