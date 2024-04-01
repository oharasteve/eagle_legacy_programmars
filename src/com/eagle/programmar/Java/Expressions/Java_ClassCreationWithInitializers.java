// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Java_ClassCreationWithInitializers extends PrimaryOperator
{
	public @S(10) Java_Keyword NEW = new Java_Keyword("new");
	public @S(20) Java_Type jtype;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) @OPT Java_ArgumentList valueList;
	public @S(50) PunctuationRightBrace rightBrace;
}
