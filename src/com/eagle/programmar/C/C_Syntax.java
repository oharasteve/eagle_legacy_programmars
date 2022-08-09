// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.C;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.CMacro.Terminals.CMacro_Comment;

public class C_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "C";
	}
	
	public C_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = "\\";
		_extraCharacters = "_";
		_commentInstance = new CMacro_Comment();    // Doesn't work at all
		_punctuationExceptions = new String[] {
				"!=", "<=", "==", ">=", "/*", "&&", "||", "..", "->",
				"++", "--", "::", "+=", "-=", "...", ">>", "<<"
		};
		
		addReservedWords(C_reservedWords);
	}
	
	// From https://www.ibm.com/docs/en/developer-for-zos/14.2.0?topic=programs-c-reserved-keywords
	// Left off _Packed which looks IBM-specific
	protected static String[] C_reservedWords = new String[] {
			"auto",
			"break",
			"case",
			"char",
			"const",
			"continue",
			"default",
			"do",
			"double",
			"else",
			"enum",
			"extern",
			"float",
			"for",
			"goto",
			"if",
			"int",
			"long",
			"register",
			"return",
			"short",
			"signed",
			"sizeof",
			"static",
			"struct",
			"switch",
			"typedef",
			"union",
			"unsigned",
			"void",
			"volatile",
			"while",
	};
}
