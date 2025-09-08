// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2025

package com.eagle.programmar.Ruby.Functions;

import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Ruby_DownToMethod extends PrecedenceOperator
{
	public @S(10) Ruby_Expression init = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Ruby_Keyword DOWNTO = new Ruby_Keyword("downto");
	public @S(40) Ruby_Expression stop = new Ruby_Expression(this, AllowedPrecedence.ANY);
}
