// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Algol68;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Algol68_Program extends EagleLanguage
{
	public static final String ALGOL68 = "Algol68";
	
	public Algol68_Program()
	{
		super(ALGOL68, new Algol68_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) TokenList<Algol68_Element> elements;
	
	public static class Algol68_Element extends TokenChooser
	{
		public @CHOICE Algol68_Statement statement;
		
		public @CHOICE static class Algol68_Main extends TokenSequence
		{
			public @S(10) Algol68_Keyword MAIN = new Algol68_Keyword("MAIN");
			public @S(20) PunctuationColon colon;
			public @S(30) PunctuationLeftParen leftParen;
			public @S(40) TokenList<Algol68_Element> elements;
			public @S(50) PunctuationRightParen rightParen;
		}
	}
}
