// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.core.EagleSyntax;

public class FSharp_Syntax extends EagleSyntax
{
	public static final boolean IS_CASE_SENSITIVE = false;

	@Override
	public String syntaxId()
	{
		return "FSharp";
	}

	public FSharp_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_extraCharacters = "";
		_autoAdvance = false;
		_punctuationExceptions = new String[] {
				"<-", "..", "[|", "|]", "||", "<>", "<=", ">="
		};

		addReservedWords(FSharp_Reserved_Words.RESERVED_WORDS);
	}

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
