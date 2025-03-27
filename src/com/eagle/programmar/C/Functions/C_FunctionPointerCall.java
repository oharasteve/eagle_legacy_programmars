// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Functions;

import com.eagle.programmar.C.C_ArgumentList;
import com.eagle.programmar.C.C_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_FunctionPointerCall extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen1;
	public @S(20) @OPT PunctuationStar star2;
	public @S(30) C_Variable methodName;
	public @S(40) PunctuationRightParen rightParen1;
	public @S(50) PunctuationLeftParen leftParen2;
	public @S(60) @OPT C_ArgumentList argList;
	public @S(70) PunctuationRightParen rightParen2;
}
