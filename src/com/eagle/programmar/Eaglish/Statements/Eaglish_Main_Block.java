// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.programmar.Eaglish.Eaglish_Statement;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Eaglish_Main_Block extends TokenSequence
{
	public @S(10) Eaglish_Keyword MAIN = new Eaglish_Keyword("MAIN");
	public @S(20) Eaglish_EndOfLine eoln1;

	public @S(30) @OPT TokenList<Eaglish_Statement> statements;

	public @S(40) Eaglish_Keyword END_MAIN = new Eaglish_Keyword("END_MAIN");
	public @S(50) Eaglish_EndOfLine eoln2;
}
