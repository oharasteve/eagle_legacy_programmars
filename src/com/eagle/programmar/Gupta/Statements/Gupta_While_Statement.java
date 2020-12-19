// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 13, 2011

package com.eagle.programmar.Gupta.Statements;

import com.eagle.programmar.Gupta.Gupta_Condition;
import com.eagle.programmar.Gupta.Gupta_Statement;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Gupta_While_Statement extends TokenSequence
{
	public @S(10) Gupta_Keyword While = new Gupta_Keyword("While");
	public @S(20) Gupta_Condition condition;
	public @S(30) TokenList<Gupta_Statement> statements;
}
