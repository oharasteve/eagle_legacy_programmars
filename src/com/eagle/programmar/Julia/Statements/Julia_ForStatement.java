// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Julia_Statement;
import com.eagle.programmar.Julia.Julia_Variable;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Julia_ForStatement extends TokenSequence
{
	public @S(10) Julia_Keyword FOR = new Julia_Keyword("for");
	public @S(20) Julia_Variable var;
	public @S(30) Julia_Keyword IN = new Julia_Keyword("in");
	public @S(40) Julia_Expression values;
	public @S(50) Julia_EOLN eoln1;
	public @S(60) TokenList<Julia_Statement> statements;
	public @S(70) Julia_Keyword END = new Julia_Keyword("end");
	public @S(80) Julia_EOLN eoln2;
}
