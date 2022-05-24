// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 23, 2022

package com.eagle.programmar.Fortran;

import com.eagle.core.EagleSyntax;

public class Fortran_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Fortran";
	}
	
	public Fortran_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "";
		//_commentInstance = new Fortran_Comment();
		_punctuationExceptions = new String[] { "/=" };
		
		addReservedWords(keywords);
	}
	
	private String[] keywords = new String[] {
		"function",
	};
}
