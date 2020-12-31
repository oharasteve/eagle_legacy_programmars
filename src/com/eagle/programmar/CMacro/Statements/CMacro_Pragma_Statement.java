// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Number;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Pragma_Statement extends TokenSequence implements CMacro_Processable
{
	public @S(10) CMacro_Punctuation pound = new CMacro_Punctuation('#'); 
	public @S(20) @DOC("Pragmas.html") CMacro_Keyword PRAGMA = new CMacro_Keyword("pragma");
	public @S(30) CMacro_Pragma_Type what;
	public @S(50) CMacro_EndOfLine endOfLine;
	
	public static class CMacro_Pragma_Type extends TokenChooser
	{
		public @CHOICE CMacro_Keyword ONCE = new CMacro_Keyword("once");
		
		public @CHOICE static class CMacro_Pragma_Warning extends TokenSequence
		{
			public @S(10) CMacro_Keyword WARNING = new CMacro_Keyword("warning");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) CMacro_Keyword DISABLE = new CMacro_Keyword("disable");
			public @S(40) PunctuationColon colon;
			public @S(50) TokenList<CMacro_Number> codes;
			public @S(60) PunctuationRightParen rightParen;
		}
		
		public @CHOICE static class CMacro_Pragma_Pack extends TokenSequence
		{
			public @S(10) CMacro_Keyword PACK = new CMacro_Keyword("pack");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) @OPT CMacro_Keyword PUSH = new CMacro_Keyword("push");
			public @S(40) PunctuationRightParen rightParen;
		}
	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		// Nothing to do
		return false;	// false means we didn't change anything
	}
}
