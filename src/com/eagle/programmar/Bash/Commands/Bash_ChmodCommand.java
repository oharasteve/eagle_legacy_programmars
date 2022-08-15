// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 21, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_ChmodCommand extends TokenSequence
{
	public @S(10) Bash_Keyword CHMOD = new Bash_Keyword("chmod");
	public @S(20) @OPT TokenList<Bash_ChmodOption> options;
	public @S(30) TokenList<Bash_ChmodCode> codes;
	public @S(40) Bash_FilenameOrLiteral filename;
	
	public static class Bash_ChmodOption extends TokenChooser
	{
		public @CHOICE Bash_KeywordChoice Re = new Bash_KeywordChoice("-R", "-e");
	}
	
	public static class Bash_ChmodCode extends TokenChooser
	{
		public @CHOICE Bash_Number number;
		
		public @CHOICE static class Bash_ChmodNoGroup extends TokenSequence
		{
			public @S(10) Bash_Punctuation PLUS = new Bash_Punctuation("+");
			public @S(20) Bash_KeywordChoice RWX = new Bash_KeywordChoice("r", "w", "x");
		}
		
		public @CHOICE static class Bash_ChmodLetters extends TokenSequence
		{
			public @S(10) Bash_KeywordChoice AUGO = new Bash_KeywordChoice("a", "u", "g", "o", "go");
			public @S(20) Bash_PunctuationChoice direction = new Bash_PunctuationChoice("+", "-", "=");
			public @S(30) Bash_KeywordChoice RWX = new Bash_KeywordChoice("r", "w", "x");
		}
	}
}
