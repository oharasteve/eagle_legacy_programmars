// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class CSharp_PostIncrementExpression extends PrimaryOperator
{
	public @S(10) CSharp_Variable var;
	public @S(20) @NOSPACE CSharp_Punctuation postIncrementOperator = new CSharp_Punctuation("++");
}
