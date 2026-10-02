// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: TypeScript casts, x as T and x satisfies T.

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.TypeScript.TS_Type;
import com.eagle.tokens.PrecedenceOperator;

public class Javascript_AsExpression extends PrecedenceOperator
{
	public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Javascript_KeywordChoice AS = new Javascript_KeywordChoice("as", "satisfies");
	public @S(30) TS_Type type;
}
