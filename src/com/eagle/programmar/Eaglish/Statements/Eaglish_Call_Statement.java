// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Eaglish_Call_Statement extends TokenSequence
{
	public @S(10) Eaglish_Keyword CALL = new Eaglish_Keyword("CALL");
	public @S(20) Eaglish_Identifier_Reference name;
	public @S(30) @OPT Eaglish_CallParameters callParams;
	public @S(40) Eaglish_EndOfLine eoln;
	
	public static class Eaglish_CallParameters extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParenn;
		public @S(20) SeparatedList<Eaglish_Expression, PunctuationComma> args;
		public @S(30) PunctuationRightParen rightParenn;
	}
}
