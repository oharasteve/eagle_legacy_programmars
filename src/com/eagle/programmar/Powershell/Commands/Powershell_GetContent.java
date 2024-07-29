// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

package com.eagle.programmar.Powershell.Commands;

import com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Powershell_GetContent extends PrimaryOperator
{
	public @S(10) Powershell_Keyword GETCONTENT = new Powershell_Keyword("Get-Content");
	public @S(20) Powershell_GCParam param;
	
	public static class Powershell_GCParam extends TokenChooser
	{
		public @CHOICE Powershell_Literal XXliteral;
		public @CHOICE Powershell_Filename XXfileName;
		
		public @CHOICE static class Power_GCEncoding extends TokenSequence
		{
			public @S(10) Powershell_Keyword ENCODING = new Powershell_Keyword("-Encoding");
			public @S(20) Powershell_KeywordChoice encoding = new Powershell_KeywordChoice("Byte", "UTF8");
		}
	}
}
