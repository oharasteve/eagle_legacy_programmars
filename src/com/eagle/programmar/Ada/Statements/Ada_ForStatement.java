// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Ada_Statement;
import com.eagle.programmar.Ada.Ada_Variable;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_ForStatement extends TokenSequence
{
	public @S(10) Ada_Keyword FOR = new Ada_Keyword("for");
	public @S(20) Ada_Variable var;
	public @S(30) Ada_Keyword IN = new Ada_Keyword("in");
	public @S(40) @OPT Ada_Keyword REVERSE = new Ada_Keyword("reverse");
	public @S(50) Ada_Expression values;
	public @S(60) Ada_Keyword LOOP = new Ada_Keyword("loop");
	public @S(70) TokenList<Ada_Statement> statements;
	public @S(80) Ada_Keyword END = new Ada_Keyword("end");
	public @S(90) Ada_Keyword LOOP2 = new Ada_Keyword("loop");
	public @S(100) PunctuationSemicolon semicolon;
}
