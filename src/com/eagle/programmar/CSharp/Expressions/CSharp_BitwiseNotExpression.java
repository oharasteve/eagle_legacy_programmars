// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class CSharp_BitwiseNotExpression extends PrimaryOperator
{
	public @S(10) CSharp_Punctuation logicalNotOperator = new CSharp_Punctuation('~');
	public @S(20) CSharp_Expression expr;
}
