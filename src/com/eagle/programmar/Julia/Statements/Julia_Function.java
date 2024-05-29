// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.programmar.Julia.Julia_Statement;
import com.eagle.programmar.Julia.Julia_Variable;
import com.eagle.programmar.Julia.Symbols.Julia_Function_Definition;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Julia_Function extends TokenSequence implements AbstractFunction
{
	public @S(10) @DOC("manual/functions/") Julia_Keyword FUNCTION = new Julia_Keyword("function");
	public @S(20) Julia_Function_Definition id;
	public @S(30) @OPT Julia_FunctionParams params;
	public @S(40) Julia_EOLN eoln1;
	public @S(50) TokenList<Julia_Statement> stmts;
	public @S(60) Julia_Keyword END = new Julia_Keyword("end");
	public @S(70) Julia_EOLN eoln2;

	public static class Julia_FunctionParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Julia_Variable, PunctuationComma> parameters;
		public @S(30) PunctuationRightParen rightParen;
	}
}
