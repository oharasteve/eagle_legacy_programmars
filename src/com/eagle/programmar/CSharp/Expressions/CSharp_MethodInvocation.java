// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_ArgumentList;
import com.eagle.programmar.CSharp.CSharp_Type.CSharp_GenericType;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_MethodInvocation extends PrimaryOperator
{
	public @S(10) CSharp_Variable methodName;
	public @S(20) @OPT CSharp_GenericType generic;
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @OPT @NOSPACE CSharp_ArgumentList argList;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;
}
