// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Expressions;

import com.eagle.programmar.RPGFree.Symbols.RPGFree_Identifier_Reference;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationStar;

public class RPGFree_VariableExpression extends PrimaryOperator
{
	public @S(10) @OPT PunctuationStar star;
	public @S(20) RPGFree_Identifier_Reference id;
}
