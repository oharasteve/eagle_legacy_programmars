// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP;

import com.eagle.core.EagleSyntax;

public class JavaP_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = true;
	
	@Override
	public String syntaxId()
	{
		return "Java";
	}

	public JavaP_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = null;
		_autoAdvance = false;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] {
				"//", ";;"
		};

		addReservedWords(keywords);
	}

	private String[] keywords = new String[] {
			"class", "const", "public",
	};

	public static final String[] ACC_CODES = new String[] {
			"ACC_ABSTRACT",
			"ACC_ANNOTATION",
			"ACC_BRIDGE",
			"ACC_ENUM",
			"ACC_FINAL",
			"ACC_INTERFACE",
			"ACC_PRIVATE",
			"ACC_PROTECTED",
			"ACC_PUBLIC",
			"ACC_STATIC",
			"ACC_SUPER",
			"ACC_SYNCHRONIZED",
			"ACC_SYNTHETIC",
			"ACC_VARARGS",
			"ACC_VOLATILE"
	};
}
