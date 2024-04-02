// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Expressions;

import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class SQL_DollarVariable extends PrimaryOperator
{
	public @S(10) SQL_Punctuation dollar = new SQL_Punctuation('$');
	public @S(20) SQL_Number number;
}
