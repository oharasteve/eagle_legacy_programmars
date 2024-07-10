// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Powershell_ExpressionList;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_MethodInvocation extends PrimaryOperator
{
	public @S(10) Powershell_Variable methodName;
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @OPT @NOSPACE Powershell_ExpressionList argList;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;
}
