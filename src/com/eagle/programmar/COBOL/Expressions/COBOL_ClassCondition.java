// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class COBOL_ClassCondition extends PrimaryOperator
{
	public @S(10) COBOL_Variable var;
	public @S(20) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
	public @S(30) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
	public @S(40) COBOL_KeywordChoice type = new COBOL_KeywordChoice("ALPHABETIC", "ALPHABETIC-LOWER",
			"ALPHABETIC-UPPER", "NEGATIVE", "NUMERIC", "POSITIVE", "ZERO");
}