// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_SizeOfExpr extends PrimaryOperator
{
	public @S(10) C_Keyword SIZEOF = new C_Keyword("sizeof");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Expression expr;
	public @S(40) PunctuationRightParen rightParen;
}
