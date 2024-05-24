// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class FSharp_FunctionCall extends PrimaryOperator
{
	public @S(10) FSharp_Variable functionName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<FSharp_Expression, PunctuationComma> argList;
	public @S(40) PunctuationRightParen rightParen;
}
