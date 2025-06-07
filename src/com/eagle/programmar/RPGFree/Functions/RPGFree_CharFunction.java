// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Functions;

import com.eagle.programmar.RPGFree.RPGFree_Expression;
import com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword;
import com.eagle.programmar.RPGFree.Terminals.RPGFree_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class RPGFree_CharFunction extends PrimaryOperator
{
	public @S(10) RPGFree_Punctuation percent = new RPGFree_Punctuation("%");
	public @S(20) RPGFree_Keyword CHAR = new RPGFree_Keyword("char");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) RPGFree_Expression expr;
	public @S(50) PunctuationRightParen rightParen;
}
