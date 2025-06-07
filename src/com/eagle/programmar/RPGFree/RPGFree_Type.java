// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree;

import com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword;
import com.eagle.programmar.RPGFree.Terminals.RPGFree_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class RPGFree_Type extends TokenChooser
{
	public @CHOICE static class RPGFree_TypePacked extends TokenSequence
	{
		public @S(10) RPGFree_Keyword PACKED = new RPGFree_Keyword("packed");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) RPGFree_Number num1;
		public @S(40) PunctuationColon colon;
		public @S(50) RPGFree_Number num2;
		public @S(60) PunctuationRightParen rightParen;
	}
}
