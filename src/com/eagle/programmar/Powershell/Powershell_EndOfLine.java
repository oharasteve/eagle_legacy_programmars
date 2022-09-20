// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
import com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
import com.eagle.programmar.Powershell.Terminals.Powershell_Number;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_RealEndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Powershell_EndOfLine extends TokenSequence
{
	public @S(10) @OPT TokenList<Powershell_Redirect> redirect;
	public @S(20) @OPT Powershell_Pipe pipe;
	public @S(30) @OPT Powershell_RealEndOfLine eoln;

	public static class Powershell_Redirect extends TokenSequence
	{
		public @S(10) Powershell_PunctuationChoice direction = new Powershell_PunctuationChoice(
				"<", ">", ">>", "&>", "1>", "2>", "*>");
		public @S(20) Powershell_RedirectTo where;
		
		public static class Powershell_RedirectTo extends TokenChooser
		{
			public @CHOICE Powershell_Literal literal;
			public @CHOICE Powershell_Filename fileName;
			public @CHOICE Powershell_Variable variable;
			
			public @CHOICE static class Powershell_RedirectToNumber extends TokenSequence
			{
				public @S(10) @OPT Powershell_Punctuation ampersane = new Powershell_Punctuation("&");
				public @S(20) Powershell_Number number;
			}
		}
	}
	
	public static class Powershell_Pipe extends TokenSequence
	{
		public @S(10) Powershell_PunctuationChoice separator = new Powershell_PunctuationChoice(
				"|", ";", "||", "&&");
		public @S(20) @OPT Powershell_RealEndOfLine eoln;
		public @S(30) Powershell_Statement statement;
	}
}
