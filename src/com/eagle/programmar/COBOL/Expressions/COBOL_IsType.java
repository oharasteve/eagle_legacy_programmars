// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class COBOL_IsType extends PrecedenceOperator
{
	public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) COBOL_Keyword IS = new COBOL_Keyword("IS");
	public @S(30) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
}
