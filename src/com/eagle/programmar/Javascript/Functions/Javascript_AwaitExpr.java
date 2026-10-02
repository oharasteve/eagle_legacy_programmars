// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 1, 2026: await before any expression, not only a call:
// await (await fetch(url)).json(). Javascript_AwaitFunctionCall is tried first.

package com.eagle.programmar.Javascript.Functions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_AwaitExpr extends PrimaryOperator
{
	public @S(10) Javascript_Keyword AWAIT = new Javascript_Keyword("await");
	public @S(20) Javascript_Expression expr;
}
