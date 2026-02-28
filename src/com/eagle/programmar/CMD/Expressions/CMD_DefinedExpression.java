// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 27, 2026

package com.eagle.programmar.CMD.Expressions;

import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class CMD_DefinedExpression extends PrimaryOperator
{
	public @S(10) CMD_Keyword DEFINED = new CMD_Keyword("DEFINED");
	public @S(20) CMD_Expression expr;
}
