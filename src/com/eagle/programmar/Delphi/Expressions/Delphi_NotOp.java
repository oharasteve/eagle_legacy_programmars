// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Delphi_NotOp extends PrimaryOperator
{
	public @S(10) Delphi_Keyword NOT = new Delphi_Keyword("Not");
	public @S(20) Delphi_Expression expr;
}
