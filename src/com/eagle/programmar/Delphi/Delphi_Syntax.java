// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.Delphi;

import com.eagle.core.EagleSyntax;

public class Delphi_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Delphi";
	}
	
	public Delphi_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "";
		//_commentInstance = new Delphi_Comment();
		_punctuationExceptions = new String[] { "<>", "<=", ">=", ":=", ".." };
		
		addReservedWords(reservedWords);
	}
	
	// From https://en.wikibooks.org/wiki/Delphi_Programming/Reserved_keywords
	private static String[] reservedWords = new String[] {
			"and",
			"array",
			"as",
			"asm",
			"begin",
			"case",
			"class",
			"const",
			"constructor",
			"destructor",
			"dispinterface",
			"div",
			"do",
			"downto",
			"else",
			"end",
			"except",
			"exports",
			"file",
			"finalization",
			"finally",
			"for",
			"function",
			"goto",
			"if",
			"implementation",
			"in",
			"inherited",
			"initialization",
			"inline",
			"interface",
			"is",
			"label",
			"library",
			"mod",
			"nil",
			"not",
			"object",
			"of",
			"or",
			// "out",	// Not really reserved. And often used.
			"packed",
			"procedure",
			"program",
			"property",
			"raise",
			"record",
			"repeat",
			"resourcestring",
			"set",
			"shl",
			"shr",
			"string",
			"then",
			"threadvar",
			"to",
			"try",
			"type",
			"unit",
			"until",
			"uses",
			"var",
			"while",
			"with",
			"xor",
	};
}
