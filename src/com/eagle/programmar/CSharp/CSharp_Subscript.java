// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp;

import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class CSharp_Subscript extends TokenSequence
{
	public @S(10) @NOSPACE PunctuationLeftBracket leftBracket;
	public @S(20) @NOSPACE CSharp_Expression expr;
	public @S(30) @OPT @NOSPACE TokenList<CSharp_MoreSubscripts> more;
	public @S(40) @NOSPACE PunctuationRightBracket rightBracket;
	
	public static class CSharp_MoreSubscripts extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) CSharp_Expression expr;
	}
}
