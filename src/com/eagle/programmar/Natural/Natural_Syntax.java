// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.Natural;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.Natural.Natural_Option.Natural_OptionChoice;

public class Natural_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Natural";
	}

	public Natural_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = null;
		_extraCharacters = "-";
		_punctuationExceptions = new String[] {
				"/*"
		};

		addReservedWords(Natural_Reserved_Words.RESERVED_WORDS);

		// Careful, some start with a Label and don't get picked up.
		findFirstWords(Natural_Statement.class);

		// Pick up all the Option keywords used in DISPLAY and WRITE statements
		for (Class<?> cls : Natural_OptionChoice.class.getDeclaredClasses())
		{
			String name = cls.getDeclaredFields()[0].getName();
			// System.out.println("**** Adding Natural_Option Natural_Keyword: " + name);
			addReservedWord(name);
		}

		// Pick up the built-in functions
		for (String name : Natural_Functions.builtinFunctions)
		{
			addReservedWord(name);
		}
	}
}
