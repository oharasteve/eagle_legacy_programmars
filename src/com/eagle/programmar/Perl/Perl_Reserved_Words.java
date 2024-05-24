// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2022

package com.eagle.programmar.Perl;

public class Perl_Reserved_Words
{
	public static final String[] RESERVED_WORDS = new String[] {
			"CORE",
			"and",
			"case", // I added
			"cmp",
			"continue",
			"default", // I added
			"die", // I added
			"do",
			"else",
			"elsif",
			"eq",
			"exp",
			"for",
			"foreach",
			"function", // I added
			"ge",
			"gt",
			"if",
			"join", // I added
			"le",
			"lock",
			"lt",
			// "m",
			"ne",
			"no",
			"or",
			"package",
			"print",
			// "q", // These are funny quotes for string literals
			"qq", // See
					// https://blog.katastros.com/a?ID=00250-48fe9f7b-2996-4c75-ac9a-b54ab4ae0ef1
			"qr",
			"qw",
			"qx",
			// "s",
			"sub",
			"switch", // I added
			"tr",
			"unless",
			"until",
			"while",
			"xor",
			// "y",
	};
}
