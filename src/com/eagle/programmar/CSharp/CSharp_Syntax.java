// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.CSharp;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;

public class CSharp_Syntax extends EagleSyntax
{
	public static boolean isCaseSensitive = true;

	@Override
	public String syntaxId()
	{
		return "CSharp";
	}
	
	public CSharp_Syntax()
	{
		_isCaseSensitive = isCaseSensitive;
		_continuationChar = null;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] { "!=", "<=", "==", ">=", "=>", "//", "...", "::" };
		_commentInstance = new CSharp_Comment();
		
		addReservedWords(reservedWords);
	}
	
	// From https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/
	private static String[] reservedWords = new String[] {
			"abstract",
			"as",
			"base",
			"bool",
			"break",
			"byte",
			"case",
			"catch",
			"char",
			"checked",
			"class",
			"const",
			"continue",
			"decimal",
			"default",
			"delegate",
			"do",
			"double",
			"else",
			"enum",
			"event",
			"explicit",
			"extern",
			"false",
			"finally",
			"fixed",
			"float",
			"for",
			"foreach",
			"goto",
			"if",
			"implicit",
			"in",
			"int",
			"interface",
			"internal",
			"is",
			"lock",
			"long",
			"namespace",
			"new",
			"null",
			"object",
			"operator",
			"out",
			"override",
			"params",
			"private",
			"protected",
			"public",
			"readonly",
			"ref",
			"return",
			"sbyte",
			"sealed",
			"short",
			"sizeof",
			"stackalloc",
			"static",
			"string",
			"struct",
			"switch",
			"this",
			"throw",
			"true",
			"try",
			"typeof",
			"unit",
			"ulong",
			"unchecked",
			"unsafe",
			"ushort",
			"using",
			"virtual",
			"void",
			"volatile",
			"while",
	};
}
