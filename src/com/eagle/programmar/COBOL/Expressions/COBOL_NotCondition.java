// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class COBOL_NotCondition extends PrimaryOperator
{
	public @S(10) COBOL_Keyword NOT = new COBOL_Keyword("NOT");
	public @S(20) COBOL_Expression cond;
}
