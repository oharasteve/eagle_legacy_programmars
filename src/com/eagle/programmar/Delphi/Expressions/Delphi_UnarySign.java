// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Delphi_UnarySign extends PrimaryOperator
{
	public @S(10) Delphi_PunctuationChoice sign = new Delphi_PunctuationChoice("-", "+");
	public @S(20) Delphi_Expression expr;
}
