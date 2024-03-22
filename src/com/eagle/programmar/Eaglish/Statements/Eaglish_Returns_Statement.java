// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.programmar.Eaglish.Eaglish_Type;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.TokenSequence;

public class Eaglish_Returns_Statement extends TokenSequence
{
	public @S(10) Eaglish_Keyword RETURNS = new Eaglish_Keyword("RETURNS");
	public @S(20) Eaglish_Type type;
	public @S(30) Eaglish_EndOfLine eoln;
}
