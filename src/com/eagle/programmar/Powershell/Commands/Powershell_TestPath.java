// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 28, 2024

package com.eagle.programmar.Powershell.Commands;

import com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;

public class Powershell_TestPath extends PrimaryOperator
{
	public @S(10) Powershell_Keyword TESTPATH = new Powershell_Keyword("Test-Path");
	public @S(20) Powershell_TPParam param;
	
	public static class Powershell_TPParam extends TokenChooser
	{
		public @CHOICE Powershell_Literal XXliteral;
		public @CHOICE Powershell_Filename XXfileName;
	}
}
