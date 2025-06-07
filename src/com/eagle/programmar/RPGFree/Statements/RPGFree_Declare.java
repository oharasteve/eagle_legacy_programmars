// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Statements;

import com.eagle.programmar.RPGFree.RPGFree_Type;
import com.eagle.programmar.RPGFree.Symbols.RPGFree_Variable_Definition;
import com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class RPGFree_Declare extends TokenSequence
{
	public @S(10) RPGFree_Keyword DCL = new RPGFree_Keyword("dcl-s");
	public @S(20) RPGFree_Variable_Definition variable;
	public @S(30) RPGFree_Type type;
	public @S(40) PunctuationSemicolon semicolon;
}
