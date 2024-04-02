// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_EvaluateExpression extends PrimaryOperator
{
	public @S(10) Powershell_Punctuation dollar = new Powershell_Punctuation("$");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Powershell_Expression expression;
	public @S(40) PunctuationRightParen rightParen;
}
