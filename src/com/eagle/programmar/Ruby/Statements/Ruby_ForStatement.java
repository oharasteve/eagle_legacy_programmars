// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Statement;
import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Ruby_ForStatement extends TokenSequence
{
	public @S(10) @DOC("control_expressions_rdoc.html#label-for+Loop") Ruby_Keyword FOR = new Ruby_Keyword("for");
	public @S(20) Ruby_Variable var;
	public @S(30) Ruby_Keyword IN = new Ruby_Keyword("in");
	public @S(40) Ruby_Expression values;
	public @S(50) Ruby_EOLN eoln1;
	public @S(60) TokenList<Ruby_Statement> statements;
	public @S(70) Ruby_Keyword END = new Ruby_Keyword("end");
	public @S(80) Ruby_EOLN eoln2;
}
