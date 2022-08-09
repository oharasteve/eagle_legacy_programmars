// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Syntax;

public class CPlus_Syntax extends C_Syntax
{
	@Override
	public String syntaxId()
	{
		return "Cpp";
	}
	
	public CPlus_Syntax()
	{
		addReservedWords(C_Syntax.C_reservedWords);
		addReservedWords(reservedWords);
	}
	
	private static String[] reservedWords = new String[] {
		"class",
		"false",
		"namespace",
		"new",
		"operator",
		"true",
		"using"
	};
}
