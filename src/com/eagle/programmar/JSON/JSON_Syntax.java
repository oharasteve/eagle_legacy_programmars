// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.JSON.Terminals.JSON_Comment;

public class JSON_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;

	@Override
	public String syntaxId()
	{
		return "JSON";
	}

	public JSON_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = null;
		_extraCharacters = "";
		_commentInstance = new JSON_Comment();
	}
}
