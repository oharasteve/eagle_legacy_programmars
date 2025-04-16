// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM;

import com.eagle.core.EagleSyntax;

public class IntelASM_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;
	
	@Override
	public String syntaxId()
	{
		return "IntelASM";
	}

	public IntelASM_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = "_";
		_extraCharacters = "";
		_autoAdvance = false;

		addReservedWords(RESERVED_WORDS);
		addReservedWords(IntelASM_Register._REGISTERS);
	}

	private static final String[] RESERVED_WORDS = new String[] {
			"BYTE", "COUNT", "DWORD", "PTR"
	};
}
