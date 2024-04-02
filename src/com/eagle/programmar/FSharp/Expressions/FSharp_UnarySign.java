// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.Terminals.FSharp_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class FSharp_UnarySign extends PrimaryOperator
{
	public @S(10) FSharp_PunctuationChoice sign = new FSharp_PunctuationChoice("-");
	public @S(20) FSharp_Expression expr;
}
