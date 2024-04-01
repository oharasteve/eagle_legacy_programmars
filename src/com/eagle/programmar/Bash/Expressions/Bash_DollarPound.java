// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Bash_DollarPound extends PrimaryOperator
{
	public @S(10) Bash_PunctuationChoice dollarPound = new Bash_PunctuationChoice("$#", "$?", "$@", "$*");
}
