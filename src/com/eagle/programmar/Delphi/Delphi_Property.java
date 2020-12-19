// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Symbols.Delphi_Property_Definition;
import com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Property extends TokenSequence
{
	public @S(10) Delphi_Keyword PROPERTY = new Delphi_Keyword("Property");
	public @S(20) Delphi_Property_Definition property;
	public @S(30) @OPT Delphi_PropertySubscript subscript;
	public @S(40) PunctuationColon colon;
	public @S(50) Delphi_Type type;
	public @S(60) TokenList<Delphi_PropertyReadWrite> readWrites;
	public @S(70) PunctuationSemicolon semicolon;
	
	public static class Delphi_PropertyReadWrite extends TokenChooser
	{
		public @CHOICE static class Delphi_PropertyRead extends TokenSequence
		{
			public @S(10) Delphi_Keyword READ = new Delphi_Keyword("Read");
			public @S(20) Delphi_Identifier_Reference readVar;
		}
		
		public @CHOICE static class Delphi_PropertyWrite extends TokenSequence
		{
			public @S(10) Delphi_Keyword WRITE = new Delphi_Keyword("Write");
			public @S(20) Delphi_Identifier_Reference writeVar;
		}
	}
	
	public static class Delphi_PropertySubscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Delphi_Variable_Definition var;
		public @S(30) PunctuationColon colon;
		public @S(40) Delphi_Type type;
		public @S(50) PunctuationRightBracket rightBracket;
	}

}
