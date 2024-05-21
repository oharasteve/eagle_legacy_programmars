// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Algol68;

import com.eagle.core.EagleSyntax;

public class Algol68_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Algol68";
	}
	
	public Algol68_Syntax()
	{
		_isCaseSensitive = false;
		_extraCharacters = "";
		_autoAdvance = true;
		_punctuationExceptions = new String[] {
				":=", "((", "))", "+:=", ">=", "<=", "~+", "/=" };
		
		addReservedWords(reservedWords);
	}

	// From https://en.wikipedia.org/wiki/ALGOL_68#Bold_symbols_and_reserved_words
	private static String[] reservedWords = new String[] {
			"AT",
			"BEGIN",
			"BITS",
			"BOOL",
			"BY",
			"BYTES",
			"CASE",
			"CHANNEL",
			"CHAR",
			"CO",
			"COMMENT",
			"COMPL",
			"DO",
			"EITHER",
			"ELIF",
			"ELSE",
			"EMPTY",
			"END",
			"ESAC",
			"EXIT",
			"FALSE",
			"FI",
			"FILE",
			"FLEX",
			"FOR",
			"FORMAT",
			"FROM",
			"GO",
			"GOTO",
			"HEAP",
			"IF",
			"IN",
			"INT",
			"IS",
			"ISNT",
			"LOC",
			"LONG",
			"MODE",
			"NIL",
			"NOT",
			"OD",
			"OP",
			"OUSE",
			"OUT",
			"PAR",
			"PR",
			"PRAGMAT",
			"PRIO",
			"PROC",
			"REAL",
			"REF",
			"SEMA",
			"SHORT",
			"SKIP",
			"STRING",
			"STRUCT",
			"THEN",
			"TO",
			"TRUE",
			"UNION",
			"VOID",
			"WHILE",
	};
}
