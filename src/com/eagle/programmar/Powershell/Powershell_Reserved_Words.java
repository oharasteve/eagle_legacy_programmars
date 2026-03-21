// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 11, 2022

package com.eagle.programmar.Powershell;

public class Powershell_Reserved_Words
{
	// From
	// https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_reserved_words?view=powershell-7.2
	public static final String[] RESERVED_WORDS = new String[] {
			// "assembly", // Reflection.Assembly is common
			// "base", // Why?
			"begin",
			"break",
			"catch",
			// "class", // I removed
			"command",
			"configuration",
			"continue",
			// "data", // Not really
			"define",
			"do",
			"dynamicparam",
			"else",
			"elseif",
			"end",
			"enum",
			"exit",
			// "filter", // I removed
			"finally",
			"for",
			"foreach",
			// "from", // I removed
			"function",
			"hidden",
			"if",
			"in",
			"inlinescript",
			"interface",
			"module",
			// "namespace", // I removed
			"parallel",
			// "param", // I removed
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
			// "var", // I removed
			"where", // I added
			"where-object", // I added
			"while",
			"workflow",
	};

	public static final String[] HYPHEN_WORDS = new String[] {
			"eq",
			"ne",
			"gt",
			"ge",
			"lt",
			"le",
			"match",
			"notmatch",
			"in",
			"notin",
			"and",
			"or",
			"not",
	};
}
