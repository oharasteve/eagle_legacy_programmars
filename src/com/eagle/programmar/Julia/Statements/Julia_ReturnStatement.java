// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.TokenSequence;

public class Julia_ReturnStatement extends TokenSequence
{
	public @S(10) @DOC("functions/#The-return-Keyword") Julia_Keyword RETURN = new Julia_Keyword("return");
	public @S(20) Julia_Expression expr;
	public @S(30) Julia_EOLN eoln;
}
