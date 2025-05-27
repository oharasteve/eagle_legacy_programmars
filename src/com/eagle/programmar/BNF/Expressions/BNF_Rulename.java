// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2025

package com.eagle.programmar.BNF.Expressions;

import com.eagle.programmar.BNF.Symbols.BNF_Rule_Reference;
import com.eagle.programmar.BNF.Terminals.BNF_PunctuationChoice;
import com.eagle.tokens.TokenSequence;

public class BNF_Rulename extends TokenSequence
{
	public @S(10) BNF_Rule_Reference ref;
	public @S(20) @OPT BNF_PunctuationChoice starOrPlus = new BNF_PunctuationChoice("*", "+");
}