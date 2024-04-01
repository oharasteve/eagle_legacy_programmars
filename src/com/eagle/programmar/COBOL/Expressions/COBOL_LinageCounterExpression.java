// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class COBOL_LinageCounterExpression extends PrimaryOperator
{
	public @S(10) COBOL_Keyword LINAGECOUNTER = new COBOL_Keyword("LINAGE-COUNTER");
	public @S(20) COBOL_Keyword OF = new COBOL_Keyword("OF");
	public @S(30) COBOL_Expression expression;
}
