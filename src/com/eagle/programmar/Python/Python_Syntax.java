// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 16, 2013

package com.eagle.programmar.Python;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.Python.Terminals.Python_Comment;

public class Python_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = true;

	@Override
	public String syntaxId()
	{
		return "Python";
	}

	public Python_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = "\\";
		_extraCharacters = "_";
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				">>", "|=", "+=", "-=", "*=", "/=", "__", "_$", "**", ":=", "->", "..", "..."
		};

		_commentInstance = new Python_Comment();

		addReservedWords(Python_Reserved_Words.RESERVED_WORDS);
	}

	public static class Python_Multiline_Syntax extends Python_Syntax
	{
		@Override
		public String syntaxId()
		{
			return "Python Multi";
		}

		public Python_Multiline_Syntax()
		{
			_autoAdvance = true;
		}
	}
}
