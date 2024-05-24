// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.programmar.Ruby.Ruby_Statement;
import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Symbols.Ruby_Function_Definition;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Ruby_Function extends TokenSequence
{
	public @S(10) Ruby_Keyword DEF = new Ruby_Keyword("def");
	public @S(20) Ruby_Function_Definition id;
	public @S(30) @OPT Ruby_FunctionParams params;
	public @S(40) Ruby_EOLN eoln1;
	public @S(50) TokenList<Ruby_Statement> stmts;
	public @S(60) Ruby_Keyword END = new Ruby_Keyword("end");
	public @S(70) Ruby_EOLN eoln2;

	public static class Ruby_FunctionParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Ruby_Variable, PunctuationComma> parameters;
		public @S(30) PunctuationRightParen rightParen;
	}
}
