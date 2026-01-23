// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.core.EagleSyntax;

public class Rust_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = true;

	@Override
	public String syntaxId()
	{
		return "Rust";
	}

	public Rust_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = null;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] {
				"!=",
				"%=",
				"&=",
				"&&",
				"*=",
				"+=",
				"-=",
				"->",
				"::",
				"..",
				"..=",
				"...",
				"/=",
				"<<",
				"<<=",
				"<=",
				"==",
				"=>",
				">>",
				">>=",
				">=",
				"^=",
				"|=",
				"||"
		};

		addReservedWords(Rust_Reserved_Words.RESERVED_WORDS);
	}
}