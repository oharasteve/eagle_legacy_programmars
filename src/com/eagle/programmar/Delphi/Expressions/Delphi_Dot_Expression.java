// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Delphi_Dot_Expression extends PrecedenceOperator
{
	public @S(10) Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @OPT Delphi_Punctuation caret = new Delphi_Punctuation("^");
	public @S(30) PunctuationPeriod dot;
	public @S(40) Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
}
