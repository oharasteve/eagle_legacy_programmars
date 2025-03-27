// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.PHP;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.HTML.HTML_Syntax;

public class PHP_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = true;
	
	@Override
	public String syntaxId()
	{
		return "PHP";
	}

	public PHP_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = null;
		_extraCharacters = "_";
		_punctuationExceptions = HTML_Syntax.PUNCT;
	}
}
