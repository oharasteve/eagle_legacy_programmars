// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Java_ColonColonNew extends PrecedenceOperator
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE Java_Punctuation colonColon = new Java_Punctuation("::");
	public @S(30) @NOSPACE Java_Keyword NEW = new Java_Keyword("new");
}
