// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Bash;

import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.programmar.Bash.Terminals.Bash_RealEndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_EndOfLine extends TokenSequence
{
	public @S(10) @OPT TokenList<Bash_Redirect> redirect;
	public @S(20) Bash_LineEnder ender;

	public static class Bash_Redirect extends TokenSequence
	{
		public @S(10) Bash_PunctuationChoice direction = new Bash_PunctuationChoice("<", ">", ">>", "&>");
		public @S(20) Bash_Expression fileName;
	}
	
	public static class Bash_LineEnder extends TokenChooser
	{
		public @CHOICE Bash_RealEndOfLine eoln;
		public @CHOICE Bash_PunctuationChoice separator = new Bash_PunctuationChoice("|", ";", "||", "&&");
	}
}
