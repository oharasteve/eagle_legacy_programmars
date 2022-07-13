// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Variable;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class FSharp_LetStatement extends TokenSequence
{
	public @S(10) FSharp_Keyword LET = new FSharp_Keyword("let");
	public @S(20) @OPT FSharp_Keyword MUTABLE = new FSharp_Keyword("mutable");
	public @S(30) FSharp_Variable var;
	public @S(40) PunctuationEquals equals;
	public @S(50) FSharp_Expression expr;
	public @S(60) FSharp_EndOfLine eoln;
}
