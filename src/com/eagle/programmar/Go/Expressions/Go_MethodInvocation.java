// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Syntax.Go_Multiline_Syntax;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Go_MethodInvocation extends PrimaryOperator
{
	public @S(10) Go_Variable methodName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT @SYNTAX(Go_Multiline_Syntax.class) SeparatedList<Go_Expression, PunctuationComma> argList;
	public @S(40) PunctuationRightParen rightParen;
}
