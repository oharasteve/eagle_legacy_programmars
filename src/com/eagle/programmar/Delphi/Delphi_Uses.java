// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Uses extends TokenSequence implements EagleRunnable
{
	public @S(10) Delphi_Keyword USES = new Delphi_Keyword("Uses");
	public @S(20) SeparatedList<Delphi_UseItem, PunctuationComma> uses;
	public @S(30) PunctuationSemicolon semicolon;

	public static class Delphi_UseItem extends TokenSequence
	{
		public @S(10) Delphi_Identifier_Reference id;
		public @S(20) @OPT Delphi_UseItemIn in;

		public static class Delphi_UseItemIn extends TokenSequence
		{
			public @S(10) Delphi_Keyword IN = new Delphi_Keyword("In");
			public @S(20) Delphi_Literal name;
			public @S(30) @OPT Delphi_UseItemInBraces braces;

			public static class Delphi_UseItemInBraces extends TokenSequence
			{
				public @S(10) PunctuationLeftBrace leftBrace;
				public @S(20) Delphi_Identifier_Reference id;
				public @S(30) PunctuationRightBrace rightBrace;
			}
		}
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do here
	}
}
