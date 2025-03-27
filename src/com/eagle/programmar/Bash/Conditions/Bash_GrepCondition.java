// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

package com.eagle.programmar.Bash.Conditions;

import com.eagle.programmar.Bash.Commands.Bash_GrepCommand;
import com.eagle.tokens.PrimaryOperator;

public class Bash_GrepCondition extends PrimaryOperator
{
	public @S(10) Bash_GrepCommand grepStatement;
}
