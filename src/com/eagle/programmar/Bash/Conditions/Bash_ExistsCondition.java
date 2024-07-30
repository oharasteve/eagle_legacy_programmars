// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

package com.eagle.programmar.Bash.Conditions;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Bash_ExistsCondition extends PrimaryOperator
{
	public @S(10) Bash_KeywordChoice E = new Bash_KeywordChoice("-d", "-e", "-f", "-n", "-x", "-z");
	public @S(20) Bash_FilenameOrLiteral filename;
}
