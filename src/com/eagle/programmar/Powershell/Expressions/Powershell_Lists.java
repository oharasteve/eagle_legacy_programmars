// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Powershell_ExpressionList;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_Lists extends PrimaryOperator
{
	public @S(10) @OPT Powershell_Punctuation at = new Powershell_Punctuation("@");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT Powershell_ExpressionList expressions;
	public @S(40) PunctuationRightParen rightParen;
}
