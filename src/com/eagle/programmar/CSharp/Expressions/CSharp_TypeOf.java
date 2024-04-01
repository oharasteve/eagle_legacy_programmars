// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_TypeOf extends PrimaryOperator
{
	public @S(10) CSharp_Keyword TYPEOF = new CSharp_Keyword("typeof");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CSharp_Type type;
	public @S(40) PunctuationRightParen rightParen;
}
