// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template;

import com.eagle.core.EagleSyntax;

public class Template_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;
	
	@Override
	public String syntaxId()
	{
		return "Template";
	}

	public Template_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = null;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] {
				"<=", ">=", "==", "!="
		};

		addReservedWords(RESERVED_WORDS);
	}

	private static String[] RESERVED_WORDS = new String[] {
			"and", "data", "not", "or", "print",
	};
}