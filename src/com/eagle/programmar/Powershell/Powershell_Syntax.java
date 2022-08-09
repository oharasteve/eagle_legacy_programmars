// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import com.eagle.core.EagleSyntax;

public class Powershell_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Powershell";
	}
	
	public Powershell_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = null;
		_extraCharacters = "_-";
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				"<=", ">=", "==", "!=",
				"::", "++", "--",
				"*>", ">>" };
		
		addReservedWords(reservedWords);
	}
	
	// From https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_reserved_words?view=powershell-7.2
	private static String[] reservedWords = new String[] {
			"assembly",
			"base",
			"begin",
			"break",
			"catch",
			"class",
			"command",
			"configuration",
			"continue",
			"data",
			"define",
			"do",
			"dynamicparam",
			"else",
			"elseif",
			"end",
			"enum",
			"exit",
			"filter",
			"finally",
			"for",
			"foreach",
			"from",
			"function",
			"hidden",
			"if",
			"in",
			"inlinescript",
			"interface",
			"module",
			"namespace",
			"parallel",
			"param",
			"private",
			"process",
			"public",
			"return",
			"sequence",
			"static",
			"switch",
			"throw",
			"trap",
			"try",
			"type",
			"until",
			"using",
			"var",
			"while",
			"workflow",
	};
}