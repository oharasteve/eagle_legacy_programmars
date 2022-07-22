// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 21, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Statement;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Go_SwitchStatement extends TokenSequence
{
	public @S(10) Go_Keyword SWITCH = new Go_Keyword("switch");
	public @S(20) Go_Variable variable;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) Go_EOLN eoln1;
	public @S(50) TokenList<Go_SwitchCase> switchCases;
	public @S(60) PunctuationRightBrace rightBrace;
	public @S(70) Go_EOLN eoln2;
	
	public static class Go_SwitchCase extends TokenSequence
	{
		public @S(10) Go_Keyword CASE = new Go_Keyword("case");
		public @S(20) Go_Expression expression;
		public @S(30) PunctuationColon colon;
		public @S(40) Go_EOLN eoln;
		public @S(50) TokenList<Go_Statement> statements;
	}
}
