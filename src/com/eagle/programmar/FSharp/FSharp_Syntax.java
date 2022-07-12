// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.FSharp.FSharp_Syntax;

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
				"<-", "..", "[|", "|]", "||", ".[", "<>" };
		
		addReservedWords(keywords);
	}

	private String[] keywords = new String[] {
			"do",
			"downto",
			"else",
			"if",
			"let",
			"printfn",
			"then",
			"to",
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
