// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 30, 2024

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_DirnameCommand extends TokenSequence implements AbstractStatement
{
	public @S(10) Bash_Keyword DIRNAME = new Bash_Keyword("dirname");
	public @S(20) TokenList<Bash_FilenameOrLiteral> files;
}
