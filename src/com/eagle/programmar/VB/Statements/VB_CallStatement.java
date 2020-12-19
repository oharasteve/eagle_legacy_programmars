// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class VB_CallStatement extends TokenSequence
{
	public @S(10) VB_Keyword CALL = new VB_Keyword("call");
	public @S(20) VB_Identifier_Reference sub;
	public @S(30) @OPT VB_CallParameters callParameters;
	
	public static class VB_CallParameters extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<VB_Expression,PunctuationComma> params;
		public @S(30) PunctuationRightParen rightParen;
	}
}
