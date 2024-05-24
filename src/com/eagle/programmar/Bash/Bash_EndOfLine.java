// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Bash;

import com.eagle.programmar.Bash.Terminals.Bash_Filename;
import com.eagle.programmar.Bash.Terminals.Bash_Literal;
import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.programmar.Bash.Terminals.Bash_RealEndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationAmpersand;

public class Bash_EndOfLine extends TokenSequence
{
	public @S(10) @OPT TokenList<Bash_Redirect> redirect;
	public @S(20) Bash_LineEnder ender;

	public static class Bash_Redirect extends TokenSequence
	{
		public @S(10) Bash_PunctuationChoice direction = new Bash_PunctuationChoice("<", ">", ">>", "&>", "&>>", "1>",
				"2>");
		public @S(20) Bash_RedirectTo where;

		public static class Bash_RedirectTo extends TokenChooser
		{
			public @CHOICE Bash_Literal literal;
			public @CHOICE Bash_Filename fileName;

			public @CHOICE static class Bash_RedirectToNumber extends TokenSequence
			{
				public @S(10) @OPT PunctuationAmpersand ampersand;
				public @S(20) Bash_Number number;
			}
		}
	}

	public static class Bash_LineEnder extends TokenChooser
	{
		public @CHOICE Bash_RealEndOfLine eoln;

		public @CHOICE static class Bash_Piper extends TokenSequence
		{
			public @S(10) Bash_PunctuationChoice separator = new Bash_PunctuationChoice(",", "|", ";", "||", "&&");
			public @S(20) @OPT Bash_RealEndOfLine eoln;
		}
	}
}
