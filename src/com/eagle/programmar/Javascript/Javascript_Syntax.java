// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.Javascript;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;

public class Javascript_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Javascript";
	}
	
	public Javascript_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = null;
		_extraCharacters = "_";
		_commentInstance = new Javascript_Comment();
		_punctuationExceptions = new String[] {
				"/*", "!=", "<=", "==", ">=", "//", "&&", "||", "===", "!==", "!===", "=>", "</" };
		
		addReservedWords(reservedWords);
	}
	
	// From https://www.geeksforgeeks.org/javascript-reserved-words/?ref=lbp
	private String[] reservedWords = new String[] {
			"abstract",
			"arguments",
			"boolean",
			"break",
			"byte",
			"case",
			"catch",
			"char",
			"const",
			"continue",
			"debugger",
			"default",
			"delete",
			"do",
			"double",
			"else",
			"eval",
			"false",
			"final",
			"finally",
			"float",
			"for",
			"function",
			"goto",
			"if",
			"implements",
			"in",
			"instanceof",
			"int",
			"interface",
			"long",
			"native",
			"new",
			"null",
			"package",
			"private",
			"protected",
			"public",
			"return",
			"short",
			"static",
			"switch",
			"synchronized",
			"this",
			"throw",
			"throws",
			"transient",
			"true",
			"try",
			"typeof",
			"var",
			"void",
			"volatile",
			"while",
			"with",
			"yield",
	};
}
