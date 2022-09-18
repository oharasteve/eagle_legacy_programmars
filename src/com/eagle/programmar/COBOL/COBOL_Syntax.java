// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.COBOL;

import com.eagle.core.EagleSyntax;

public abstract class COBOL_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "COBOL";
	}
	
	public COBOL_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "-";
		_punctuationExceptions = new String[] { "**" };
		
		addReservedWords(COBOL_Reserved_Words.RESERVED_WORDS);
		// findFirstWords(COBOL_Statement.class);
	}
	
	public static class COBOL_Fixed_Format_Syntax extends COBOL_Syntax
	{
		@Override
		public String syntaxId()
		{
			return "COBOL Fixed";
		}
		
		public COBOL_Fixed_Format_Syntax()
		{
			_commentColumn = 6;		// 0 = first for all these
			_earliestComment = 11;	// Comments to end-of-line must start in column 12 or later
			_fixedStartColumn = 6;
			_fixedEndColumn = 72;
		}
	}
	
	public static class COBOL_Free_Format_Syntax extends COBOL_Syntax
	{
		@Override
		public String syntaxId()
		{
			return "COBOL Free";
		}
		
		public COBOL_Free_Format_Syntax()
		{
			_commentColumn = 0;	    // Default is 0 anyways
			_earliestComment = 1;	// Comments to end-of-line must start in column 2 or later
		}
	}
}
