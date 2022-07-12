// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Statement.FSharp_SingleOrMultiLineStatement;
import com.eagle.programmar.FSharp.FSharp_Variable;
import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
import com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class FSharp_ForStatement extends TokenSequence
{
	public @S(10) @NOSPACE FSharp_Keyword FOR = new FSharp_Keyword("for");
	public @S(20) FSharp_Variable var;
	public @S(30) PunctuationEquals equals;
	public @S(40) FSharp_Expression init;
	public @S(50) FSharp_KeywordChoice TO = new FSharp_KeywordChoice("to", "downto");
	public @S(60) FSharp_Expression last;
	public @S(70) FSharp_Keyword DO = new FSharp_Keyword("do");
	public @S(80) FSharp_SingleOrMultiLineStatement forActions;
}
