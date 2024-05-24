// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.ObjectiveC;

import com.eagle.programmar.C.C_Syntax;

public class ObjectiveC_Syntax extends C_Syntax
{
	@Override
	public String syntaxId()
	{
		return "ObjC";
	}

	public ObjectiveC_Syntax()
	{
		addReservedWords(C_Syntax.C_reservedWords);
		addReservedWords(reservedWords);
	}

	private static String[] reservedWords = new String[] {
			"BOOL", "NO", "YES",
	};
}
