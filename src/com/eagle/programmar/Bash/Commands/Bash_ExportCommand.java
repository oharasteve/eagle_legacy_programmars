// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Bash_ExportCommand extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("#index-export") Bash_Keyword EXPORT = new Bash_Keyword("export");
	public @S(20) @OPT TokenList<Bash_ExportOption> options;
	public @S(30) Bash_Variable var;
	public @S(40) PunctuationEquals equals;
	public @S(50) Bash_Expression expr;

	public static class Bash_ExportOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice XXopt = new Bash_KeywordChoice("-n");
	}
}
