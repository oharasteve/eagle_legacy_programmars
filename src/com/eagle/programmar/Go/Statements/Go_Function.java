// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.programmar.Go.Go_Statement;
import com.eagle.programmar.Go.Go_Type;
import com.eagle.programmar.Go.Symbols.Go_Function_Definition;
import com.eagle.programmar.Go.Symbols.Go_Variable_Definition;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Go_Function extends TokenSequence
{
	public @S(10) Go_Keyword FUNC = new Go_Keyword("func");
	public @S(20) Go_Function_Definition id;
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) SeparatedList<Go_FunctionParamater,PunctuationComma> parameters;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) @OPT Go_Type returnType;
	public @S(70) Go_Statement stmt;
	
	public static class Go_FunctionParamater extends TokenSequence
	{
		public @S(10) Go_Variable_Definition var;
		public @S(20) Go_Type type;
	}
}
