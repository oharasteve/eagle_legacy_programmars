// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.core.EagleSyntax;

public class FSharp_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "FSharp";
	}

	public FSharp_Syntax()
	{
		_isCaseSensitive = false;
		_extraCharacters = "";
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				"<-", "..", "[|", "|]", "||", ".[", "<>", "<=", ">="
		};

		addReservedWords(reservedWords);
	}

	// From https://fsharp.org/specs/language-spec/4.1/FSharpSpec-4.1-latest.pdf
	private static String[] reservedWords = new String[] {
			"abstract",
			"and",
			"as",
			"assert",
			"atomic",
			"base",
			"begin",
			"break",
			"checked",
			"class",
			"component",
			"const",
			"constraint",
			"constructor",
			"continue",
			"default",
			"delegate",
			"do",
			"done",
			"downcast",
			"downto",
			"eager",
			"elif",
			"else",
			"end",
			"exception",
			"extern",
			"false",
			"finally",
			"fixed",
			"for",
			"fori",
			"fun",
			"function",
			"functor",
			"global",
			"if",
			"in",
			"include",
			"inherit",
			"inline",
			"interface",
			"internal",
			"lazy",
			"let",
			"match",
			"measure",
			"member",
			"method",
			"mixin",
			"module",
			"mutable",
			"namespace",
			"new",
			"not",
			"null",
			"object",
			"of",
			"open",
			"or",
			"override",
			"parallel",
			"params",
			"private",
			"process",
			"protected",
			"public",
			"pure",
			"rec",
			"recursive",
			"return",
			"sealed",
			"sig",
			"static",
			"struct",
			"tailcall",
			"then",
			"to",
			"trait",
			"true",
			"try",
			"type",
			"upcast",
			"use",
			"val",
			"virtual",
			"void",
			"volatile",
			"when",
			"while",
			"with",
			"yield",
	};

	public static class FSharp_Multiline_Syntax extends FSharp_Syntax
	{
		@Override
		public String syntaxId()
		{
			return "FSharp Multi";
		}

		public FSharp_Multiline_Syntax()
		{
			_autoAdvance = true;
		}
	}
}
