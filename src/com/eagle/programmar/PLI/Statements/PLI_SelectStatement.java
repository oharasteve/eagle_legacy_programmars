// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 19, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Statement;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_SelectStatement extends TokenSequence
{
	public @S(10) @DOC("7.51") PLI_Keyword SELECT = new PLI_Keyword("SELECT");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) PLI_Expression expr;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationSemicolon semicolon1;
	public @S(60) TokenList<PLI_SelectWhenClause> selectWhens;
	public @S(70) @OPT PLI_SelectOtherwise otherwise;
	public @S(80) PLI_Keyword END = new PLI_Keyword("END");
	public @S(90) PunctuationSemicolon semicolon2;
	
	public static class PLI_SelectWhenClause extends TokenSequence
	{
		public @S(10) PLI_Keyword WHEN = new PLI_Keyword("WHEN");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SeparatedList<PLI_Literal,PunctuationComma> literals;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) PLI_Statement statement;
	}
	
	public static class PLI_SelectOtherwise extends TokenSequence
	{
		public @S(10) PLI_Keyword OTHERWISE = new PLI_Keyword("OTHERWISE");
		public @S(20) @OPT PLI_Statement statement;
		public @S(30) @OPT PLI_Punctuation semiColon3 = new PLI_Punctuation(';');
	}
}
