// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Statements;

import com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class RPGFree_Control extends TokenSequence
{
	public @S(10) RPGFree_Keyword CTLOPT = new RPGFree_Keyword("ctl-opt");
	public @S(20) TokenList<RPGFree_ControlOption> options;
	public @S(30) PunctuationSemicolon semicolon;
	
	public static class RPGFree_ControlOption extends TokenChooser
	{
		public @CHOICE static class RPGFree_ControlDft extends TokenSequence
		{
			public @S(10) RPGFree_Keyword DFTACTGRP = new RPGFree_Keyword("dftactgrp");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) PunctuationStar star;
			public @S(40) RPGFree_Keyword NO = new RPGFree_Keyword("no");
			public @S(50) PunctuationRightParen rightParen;
		}
		
		public @CHOICE static class RPGFree_ControlAct extends TokenSequence
		{
			public @S(10) RPGFree_Keyword ACTGRP = new RPGFree_Keyword("actgrp");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) PunctuationStar star;
			public @S(40) RPGFree_Keyword CALLER = new RPGFree_Keyword("caller");
			public @S(50) PunctuationRightParen rightParen;
		}
	}
}
