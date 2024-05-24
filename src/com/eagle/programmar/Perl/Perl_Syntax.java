// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.Perl;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;

public class Perl_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Perl";
	}

	public Perl_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = null;
		_extraCharacters = "_";
		_commentInstance = new Perl_Comment();
		_punctuationExceptions = new String[] {
				"===", "!==", "::", "==", "->", "<=", ">=", "<<", ">>", "!=", "=>", "=~", "?>", "//", "/*", "**"
		};

		addReservedWords(Perl_Reserved_Words.RESERVED_WORDS);
	}
}
