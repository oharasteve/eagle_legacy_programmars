// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import com.eagle.core.EagleSyntax;

public class Powershell_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Powershell";
	}
	
	public Powershell_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] { "<=", ">=", "==", "!=", "::", "++", "--" };
		
		addReservedWords(reservedWords);
	}
	
	private String[] reservedWords = new String[] {
		"break",
		"data",
		"print",
	};
}