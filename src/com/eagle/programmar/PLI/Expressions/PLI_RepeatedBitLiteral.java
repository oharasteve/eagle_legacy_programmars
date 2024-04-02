// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Expressions;

import com.eagle.programmar.PLI.PLI_RepeatCount;
import com.eagle.programmar.PLI.Terminals.PLI_BitLiteral;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;

public class PLI_RepeatedBitLiteral extends PrimaryOperator
{
	public @S(10) TokenList<PLI_RepeatCount> repeat;
	public @S(20) PLI_BitLiteral literal;
}
