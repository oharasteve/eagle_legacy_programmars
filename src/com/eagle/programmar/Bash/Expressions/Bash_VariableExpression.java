// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.tokens.PrimaryOperator;

public class Bash_VariableExpression extends PrimaryOperator
{
	// Because Bash_Variable is not a TerminalToken, it has to be wrapped in a PrimaryOperator
	public @S(10) Bash_Variable variable;
}
