// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 9, 2025

package com.eagle.programmar.IntelASM.Expressions;

import com.eagle.programmar.IntelASM.IntelASM_Expression;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class IntelASM_DwordPtr extends PrimaryOperator
{
	public @S(10) IntelASM_Keyword DWORD = new IntelASM_Keyword("DWORD");
	public @S(20) IntelASM_Keyword PTR = new IntelASM_Keyword("PTR");
	public @S(30) PunctuationLeftBracket leftBracket;
	public @S(40) IntelASM_Expression expr;
	public @S(50) PunctuationRightBracket rightBracket;
}
