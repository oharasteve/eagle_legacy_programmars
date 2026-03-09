// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 21, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Terminals.Bash_ChmodLetters;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Bash_ChmodCommand extends TokenSequence implements AbstractStatement
{
	public @S(10) Bash_Keyword CHMOD = new Bash_Keyword("chmod");
	public @S(20) @OPT TokenList<Bash_ChmodOption> options;
	public @S(30) Bash_ChmodCode code;
	public @S(40) @OPT TokenList<Bash_ChmodMoreCodes> moreCodes;
	public @S(50) Bash_FilenameOrLiteral filename;

	public static class Bash_ChmodOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice XXopt = new Bash_KeywordChoice("-R", "-e");
	}

	public static class Bash_ChmodMoreCodes extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) Bash_ChmodCode code;
	}

	public static class Bash_ChmodCode extends TokenChooser
	{
		public @CHOICE Bash_Number XXnumber;
		public @CHOICE Bash_ChmodLetters XXletters;
	}
}
