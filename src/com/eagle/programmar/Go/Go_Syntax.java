// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Go;

import com.eagle.core.EagleSyntax;

public class Go_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Go";
	}

	public Go_Syntax()
	{
		_isCaseSensitive = true;
		_extraCharacters = "";
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				":=", "==", "!=", "<=", ">=", "++", "--", "+=", "-=", "*=", "/-", "!~"
		};

		addReservedWords(Go_Reserved_Words.RESERVED_WORDS);
	}

	public static class Go_Multiline_Syntax extends Go_Syntax
	{
		@Override
		public String syntaxId()
		{
			return "Go Multi";
		}

		public Go_Multiline_Syntax()
		{
			_autoAdvance = true;
		}
	}
}
