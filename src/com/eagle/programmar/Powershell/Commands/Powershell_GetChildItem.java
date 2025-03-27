// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 28, 2024

package com.eagle.programmar.Powershell.Commands;

import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
import com.eagle.programmar.Powershell.Terminals.Powershell_Word;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Powershell_GetChildItem extends PrimaryOperator
{
	public @S(10) Powershell_KeywordChoice GETCHILDITEM = new Powershell_KeywordChoice("Get-ChildItem", "GCI");
	public @S(20) @OPT TokenList<Powershell_GCIparam> params;
	
	public static class Powershell_GCIparam extends TokenChooser
	{
		public @CHOICE Powershell_Literal XXliteral;
		public @CHOICE Powershell_Filename XXfileName;
		public @CHOICE Powershell_Variable XXvariable;
		public @CHOICE Powershell_KeywordChoice XXopt = new Powershell_KeywordChoice(
				"-Directory",
				"-File",
				"-Filter",
				"-Force",
				"-Hidden",
				"-Path",
				"-Recurse");
		public @CHOICE Powershell_StandardOption XXstandard;
		public @LAST Powershell_Word XXword;
		
		public @CHOICE static class Powershell_GCExclude extends TokenSequence
		{
			public @S(10) Powershell_KeywordChoice EXCLUDE = new Powershell_KeywordChoice("-Exclude", "-Include");
			public @S(20) SeparatedList<Powershell_GCIparam,PunctuationComma> fileList;
		}
	}
}
