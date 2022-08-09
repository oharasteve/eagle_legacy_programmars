// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.core.EagleSyntax;

public class Rust_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Rust";
	}
	
	public Rust_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = null;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] {
				"!=","%=", "&=", "&&", "*=", "+=", "-=", "->", "..", "..=", "...", "/=",
				"<<", "<<=", "<=", "==", "=>", ">>", ">>=", "^=", "|=", "||" };
		
		addReservedWords(reservedWords);
	}
	
	// From https://doc.rust-lang.org/reference/keywords.html
	private static String[] reservedWords = new String[] {
			"Self",
			"abstract",
			"as",
			"async",
			"await",
			"become",
			"box",
			"break",
			"const",
			"continue",
			"crate",
			"do",
			"dyn",
			"else",
			"enum",
			"extern",
			"false",
			"final",
			"fn",
			"for",
			"if",
			"impl",
			"in",
			"let",
			"loop",
			"macro",
			"match",
			"mod",
			"move",
			"mut",
			"override",
			"priv",
			"pub",
			"ref",
			"return",
			"self",
			"static",
			"struct",
			"super",
			"trait",
			"true",
			"try",
			"type",
			"typeof",
			"unsafe",
			"unsized",
			"use",
			"virtual",
			"where",
			"while",
			"yield",
	};
}