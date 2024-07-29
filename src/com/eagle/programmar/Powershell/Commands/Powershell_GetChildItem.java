// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 28, 2024

package com.eagle.programmar.Powershell.Commands;

import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

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
				"-Exclude",
				"-File",
				"-Filter",
				"-Force",
				"-Hidden",
				"-Include",
				"-Path",
				"-Recurse"
				);
		public @CHOICE Powershell_StandardOption XXstandard;
	}
}
