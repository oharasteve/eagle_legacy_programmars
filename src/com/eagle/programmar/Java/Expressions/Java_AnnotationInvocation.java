// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_AnnotationInvocation extends PrimaryOperator
{
	public @S(10) Java_Punctuation at = new Java_Punctuation("@");
	public @S(20) Java_Variable methodName;
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @NOSPACE @OPT Java_ArgumentList argList;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;
}
