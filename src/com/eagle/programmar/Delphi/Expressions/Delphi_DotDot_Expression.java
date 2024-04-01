// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Delphi_DotDot_Expression extends PrecedenceOperator 
{
	public @S(10) Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Delphi_Punctuation dotDot = new Delphi_Punctuation("..");
	public @S(30) Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
}
