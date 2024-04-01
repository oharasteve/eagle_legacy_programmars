// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.Terminals.AWK_Identifier;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_UserFunctionCall extends PrimaryOperator
{
	public @S(10) AWK_Identifier functionName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT AWK_ArgumentList argList;
	public @S(40) PunctuationRightParen rightParen;
}
