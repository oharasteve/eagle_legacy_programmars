// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Bash_ExportCommand extends TokenSequence
{
	public @S(10) Bash_Keyword EXPORT = new Bash_Keyword("export");
	public @S(20) Bash_Variable var;
	public @S(30) PunctuationEquals equals;
	public @S(40) Bash_Expression expr;
}
