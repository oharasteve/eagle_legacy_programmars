// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Functions;

import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_SizeOfFunction extends PrimaryOperator
{
	public @S(10) CSharp_KeywordChoice SIZEOF = new CSharp_KeywordChoice("sizeof", "typeof");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CSharp_Type type;
	public @S(40) PunctuationRightParen rightParen;
}
