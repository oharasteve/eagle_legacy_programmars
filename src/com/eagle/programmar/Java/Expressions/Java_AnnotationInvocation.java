// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_AnnotationInvocation extends PrimaryOperator
{
	public @S(10) Java_Punctuation at = new Java_Punctuation("@");
	public @S(20) Java_Variable name;
	public @S(30) @OPT TokenList<Java_AnnotationDotName> dotNames;
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE @OPT Java_ArgumentList argList;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	public static class Java_AnnotationDotName extends TokenSequence
	{
		public @S(10) PunctuationPeriod dot;
		public @S(20) Java_Variable name;
	}
}
