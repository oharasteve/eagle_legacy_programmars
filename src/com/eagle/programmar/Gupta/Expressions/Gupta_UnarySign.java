// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Gupta.Expressions;

import com.eagle.programmar.Gupta.Gupta_Expression;
import com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Gupta_UnarySign extends PrimaryOperator
{
	public @S(10) Gupta_PunctuationChoice sign = new Gupta_PunctuationChoice("-", "+");
	public @S(20) Gupta_Expression exp;
}
