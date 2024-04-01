// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Java_ExpressionList extends PrimaryOperator
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT TokenList<Java_Comment> comment;
	public @S(30) @OPT Java_ArgumentList valueList;
	public @S(40) PunctuationRightBrace rightBrace;
}
