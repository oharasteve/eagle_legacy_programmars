// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.PLI;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;

public class PLI_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;
	
	@Override
	public String syntaxId()
	{
		return "PLI";
	}

	public PLI_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = null;
		_extraCharacters = "_";
		this._commentInstance = new PLI_Comment();
	}
}
