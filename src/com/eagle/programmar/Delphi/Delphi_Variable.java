// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Delphi_Variable extends TokenSequence implements AbstractVariable
{
	public @S(10) Delphi_Identifier_Reference var;
	public @S(20) @OPT TokenList<Delphi_Extended_Variable> extensions;

	public static class Delphi_Extended_Variable extends TokenChooser
	{
		public @CHOICE static class Delphi_DotName extends TokenSequence
		{
			public @S(10) PunctuationPeriod dot;
			public @S(20) Delphi_Identifier_Reference var;
		}
	
		public @CHOICE static class Delphi_Subscript extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) SeparatedList<Delphi_Expression,PunctuationComma> expr;
			public @S(30) PunctuationRightBracket rightBracket;
		}
	}
}
