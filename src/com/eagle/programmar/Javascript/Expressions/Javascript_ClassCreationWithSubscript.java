// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Subscript;
import com.eagle.programmar.Javascript.Javascript_Type;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;

public class Javascript_ClassCreationWithSubscript extends PrimaryOperator
{
	public @S(10) Javascript_Keyword NEW = new Javascript_Keyword("new");
	public @S(20) Javascript_Type jtype;
	public @S(30) TokenList<Javascript_Subscript> subscripts;
}
