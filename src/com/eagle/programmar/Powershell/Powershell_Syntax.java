// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import com.eagle.core.EagleSyntax;

public class Powershell_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;
	@Override
	public String syntaxId()
	{
		return "Powershell";
	}

	public Powershell_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = "`";
		_extraCharacters = "_-";
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				"<=", ">=", "==", "!=", "::", "++", "--", "*>", ">>", "$?", "$_", "<#", "#>", ".."
		};

		// Breaks everything - problem is the EOLN, not the comment itself.
		// _commentInstance = new Powershell_Comment();

		addReservedWords(Powershell_Reserved_Words.RESERVED_WORDS);
		addHyphenWords(Powershell_Reserved_Words.HYPHEN_WORDS);
	}
}