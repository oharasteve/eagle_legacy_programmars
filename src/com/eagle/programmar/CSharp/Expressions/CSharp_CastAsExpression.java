// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 19, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class CSharp_CastAsExpression extends PrecedenceOperator
{
	public @S(10) CSharp_Expression expr = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CSharp_Keyword AS = new CSharp_Keyword("as");
	public @S(30) CSharp_Type type;
}
