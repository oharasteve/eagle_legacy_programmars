// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Symbols.Powershell_Function_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.programmar.Powershell.Terminals.Powershell_VerbNoun;
import com.eagle.programmar.Powershell.Terminals.Powershell_Word;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationAmpersand;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Powershell_Command extends TokenSequence implements AbstractStatement
{
	public @S(10) Powershell_WhichCommand which; // Like Get-Content or javac
	public @S(20) @OPT TokenList<Powershell_CommandArg> arg;

	public static class Powershell_WhichCommand extends TokenChooser
	{
		public @CHOICE PunctuationAmpersand XXampersand;
		public @CHOICE PunctuationPeriod XXdot;
		public @CHOICE Powershell_Punctuation XXdotDot = new Powershell_Punctuation("..");
		public @CHOICE PunctuationColon XXcolon;
		public @CHOICE Powershell_VerbNoun XXverbNoun; // Like Get-Content for example
		public @LAST Powershell_Function_Reference XXid;
	}

	public static class Powershell_CommandArg extends TokenSequence
	{
		public @S(10) Powershell_CommandOneArg arg;
		public @S(20) @OPT PunctuationComma comma;

		public static class Powershell_CommandOneArg extends TokenChooser
		{
			public @FIRST Powershell_Filename XXfilename; // Tricky because the dot is already taken
			public @CHOICE Powershell_Expression XXexpr;
			public @LAST Powershell_Word XXword;
		}
	}
}
