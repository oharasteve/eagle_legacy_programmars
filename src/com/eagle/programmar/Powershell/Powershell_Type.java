// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Symbols.Powershell_Identifier_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Powershell_Type extends TokenSequence
{
	public @S(10) Powershell_BaseType base;
	public @S(20) @OPT Powershell_TypeBrackets brackets;

	public static class Powershell_BaseType extends TokenChooser
	{
		public @CHOICE Powershell_KeywordChoice PRIMITIVE = new Powershell_KeywordChoice(
				"Array",
				"Boolean",
				"Char",
				"DateTime",
				"Double",
				"Float",
				"Int",
				"Int32",
				"Int64",
				"Long",
				"PSCredential",
				"SecureString",
				"String");
		
		public @CHOICE static class Powershell_SystemType extends TokenSequence
		{
			public @S(10) Powershell_KeywordChoice SYSTEM = new Powershell_KeywordChoice("Net", "System");
			public @S(20) TokenList<Powershell_SystemSubType> subtype;
			
			public static class Powershell_SystemSubType extends TokenSequence
			{
				public @S(10) PunctuationPeriod dot;
				public @S(20) Powershell_Identifier_Reference id;
			}
		}
	}
	
	public static class Powershell_TypeBrackets extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) PunctuationRightBracket rightBracket;
	}
}
