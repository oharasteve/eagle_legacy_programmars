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
		_extraCharacters = "_-";
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				"<=", ">=", "==", "!=",
				"::", "++", "--",
				"*>", ">>",
				"$?" };
		
		// Breaks everything - problem is the EOLN, not the comment itself.
		// _commentInstance = new Powershell_Comment();
		
		// All user variables must start with a $
		// addReservedWords(Powershell_Reserved_Words.RESERVED_WORDS);
	}
}