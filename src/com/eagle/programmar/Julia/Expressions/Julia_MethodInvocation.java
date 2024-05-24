// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Julia_Variable;
import com.eagle.programmar.Julia.Terminals.Julia_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Julia_MethodInvocation extends PrimaryOperator
{
	public @S(10) Julia_Variable methodName;
	public @S(20) @OPT Julia_Punctuation question = new Julia_Punctuation("?");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT SeparatedList<Julia_Expression, PunctuationComma> argList;
	public @S(50) PunctuationRightParen rightParen;
}
