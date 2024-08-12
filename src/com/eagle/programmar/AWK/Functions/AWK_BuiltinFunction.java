// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Functions;

import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_BuiltinFunction extends PrimaryOperator
{
	public @S(10) AWK_KeywordChoice function = new AWK_KeywordChoice("index", "match", "strftime");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT AWK_ArgumentList argList;
	public @S(40) PunctuationRightParen rightParen;
}
