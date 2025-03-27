// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_DiffCommand extends TokenSequence implements AbstractStatement
{
	public @S(10) Bash_Keyword DIFF = new Bash_Keyword("diff");
	public @S(20) @OPT TokenList<Bash_DiffOption> options;
	public @S(30) Bash_FilenameOrLiteral fileName1;
	public @S(40) Bash_FilenameOrLiteral fileName2;

	public static class Bash_DiffOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice XXopt = new Bash_KeywordChoice("-r", "--strip-trailing-cr");
	}
}
