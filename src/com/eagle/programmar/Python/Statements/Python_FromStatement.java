// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Python_FromStatement extends TokenSequence
{
	public @S(10) @DOC("simple_stmts.html#the-import-statement") Python_Keyword FROM = new Python_Keyword("from");
	public @S(20) @OPT Python_PunctuationChoice dots = new Python_PunctuationChoice(".", "..");
	public @S(30) @OPT SeparatedList<Python_Variable, PunctuationPeriod> fromName;
	public @S(40) Python_ImportStatement importStatement;
}
