// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Pragma_Intrinsic extends TokenSequence
{
	public @S(10) CMacro_Keyword INTRINSIC = new CMacro_Keyword("intrinsic");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT CMacro_KeywordChoice ROT = new CMacro_KeywordChoice("rotl", "rotr", "_rotl", "_rotr",
			"_BitScanReverse", "_InterlockedCompareExchange", "_InterlockedCompareExchangePointer", "_umul128");
	public @S(40) PunctuationRightParen rightParen;
}
