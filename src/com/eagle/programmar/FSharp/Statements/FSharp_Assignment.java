// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Variable;
import com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
import com.eagle.tokens.TokenSequence;

public class FSharp_Assignment extends TokenSequence
{
	public @S(10) FSharp_Variable var;
	public @S(20) FSharp_Punctuation operator = new FSharp_Punctuation("<-");
	public @S(30) FSharp_Expression expr;
}
