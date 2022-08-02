// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

//[CmdletBinding()]
//Param(
//   [Parameter(Mandatory=$False,Position=1)]$param,
//   [Switch]$night = $False
//)

public class Powershell_CmdletBinding extends TokenSequence
{
	public @S(10) @OPT Powershell_CmdletBound bound;
	public @S(20) Powershell_CmdletParamList paramList;
	
	public static class Powershell_CmdletBound extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Powershell_Keyword CMDLETBINDING = new Powershell_Keyword("CmdletBinding");
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) PunctuationRightBracket rightBracket;
		public @S(60) @OPT Powershell_EndOfLine eoln;
	}
	
	public static class Powershell_CmdletParamList extends TokenSequence
	{
		public @S(10) Powershell_Keyword PARAM = new Powershell_Keyword("Param");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT Powershell_EndOfLine eoln1;
		public @S(40) TokenList<Powershell_CmdletParam> params;
		public @S(50) PunctuationRightParen rightParen;
		public @S(60) @OPT Powershell_EndOfLine eoln2;
		
		public static class Powershell_CmdletParam extends TokenChooser
		{
			public @CHOICE static class Powershell_CmdletParameter extends TokenSequence
			{
				public @S(10) PunctuationLeftBracket leftBracket;
				public @S(20) Powershell_Keyword PARAMETER = new Powershell_Keyword("Parameter");
				public @S(30) PunctuationLeftParen leftParen;
				public @S(40) SeparatedList<Powershell_CmdletParameterOption,PunctuationComma> options;
				public @S(50) PunctuationRightParen rightParen;
				public @S(60) PunctuationRightBracket rightBracket;
				public @S(70) @OPT Powershell_CastParam cast;
				public @S(80) Powershell_Variable param;
				public @S(90) @OPT PunctuationComma comma;
				public @S(100) @OPT Powershell_EndOfLine eoln;
				
				public static class Powershell_CastParam extends TokenSequence
				{
					public @S(10) PunctuationLeftBracket leftBracket;
					public @S(20) Powershell_Type type;
					public @S(30) PunctuationRightBracket rightBracket;
				}
				
				public static class Powershell_CmdletParameterOption extends TokenChooser
				{
					public @CHOICE static class Powershell_CmdletParameterMandatory extends TokenSequence
					{
						public  @S(10)Powershell_Keyword MANDATORY = new Powershell_Keyword("Mandatory");
						public  @S(20)PunctuationEquals equals;
						public  @S(30)Powershell_Expression value;
					}
					
					public @CHOICE static class Powershell_CmdletParameterPosition extends TokenSequence
					{
						public @S(10) Powershell_Keyword POSITION = new Powershell_Keyword("Position");
						public @S(20) PunctuationEquals equals;
						public @S(30) Powershell_Expression value;
					}
				}
			}
			
			public @CHOICE static class Powershell_CmdletSwitch extends TokenSequence
			{
				public @S(10) PunctuationLeftBracket leftBracket;
				public @S(20) Powershell_Keyword SWITCH = new Powershell_Keyword("Switch");
				public @S(30) PunctuationRightBracket rightBracket;
				public @S(40) Powershell_Variable param;
				public @S(50) PunctuationEquals equals;
				public @S(60) Powershell_Expression expr;
				public @S(70) @OPT PunctuationComma comma;
				public @S(80) @OPT Powershell_EndOfLine eoln;
			}
		}
	}
}
