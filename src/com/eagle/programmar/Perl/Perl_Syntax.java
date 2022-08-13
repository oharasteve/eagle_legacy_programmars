// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.Perl;

import com.eagle.core.EagleSyntax;

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
		_punctuationExceptions = new String[] {
				"===", "!==", "::", "==", "->", "<=", ">=", "<<", ">>", "!=", "=>", "=~", "?>" };
		
		addReservedWords(reservedWords);
	}

	private static final String[] reservedWords = new String[] {
			"CORE",
			"and",
			"case",			// I added
			"cmp",
			"continue",
			"default",		// I added
			"do",
			"else",
			"elsif",
			"eq",
			"exp",
			"for",
			"foreach",
			"function",		// I added
			"ge",
			"gt",
			"if",
			"le",
			"lock",
			"lt",
			// "m",
			"ne",
			"no",
			"or",
			"package",
			// "q",		// These are funny quotes for string literals
			"qq",		// See https://blog.katastros.com/a?ID=00250-48fe9f7b-2996-4c75-ac9a-b54ab4ae0ef1
			"qr",
			"qw",
			"qx",
			// "s",
			"sub",
			"switch",		// I added
			"tr",
			"unless",
			"until",
			"while",
			"xor",
			// "y",
	};
}
