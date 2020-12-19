// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_SuperStatement extends TokenSequence
{
	public @S(10) CSharp_Keyword SUPER = new CSharp_Keyword("super");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT CSharp_ArgumentList args;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationSemicolon semicolon;
}
