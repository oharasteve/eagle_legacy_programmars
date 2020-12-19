// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Type_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Types extends TokenSequence
{
	public @S(10) Delphi_Keyword TYPE = new Delphi_Keyword("Type");
	public @S(20) @OPT TokenList<Delphi_MoreTypes> moreTypes;
	
	public static class Delphi_MoreTypes extends TokenSequence
	{
		public @S(10) Delphi_Type_Definition name;
		public @S(20) PunctuationEquals equals;
		public @S(30) Delphi_Type type;
		public @S(40) PunctuationSemicolon semicolon;
	}
}
