// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

package com.eagle.programmar.Bash.Conditions;

import com.eagle.programmar.Bash.Bash_Condition;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Bash_LogicalAndCondition extends PrecedenceOperator
{
	public @S(10) Bash_Condition left = new Bash_Condition(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationHyphen dash;
	public @S(30) Bash_Keyword AND = new Bash_Keyword("and");
	public @S(40) Bash_Condition right = new Bash_Condition(this, AllowedPrecedence.HIGHER);
}
