// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Statement;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_HistogramStatement extends TokenSequence
{
	public @S(10) @DOC("sm/histogra.htm") Natural_Keyword HISTOGRAM = new Natural_Keyword("HISTOGRAM");
	public @S(20) Natural_Identifier_Reference viewName;
	public @S(30) Natural_Variable var;
	public @S(40) Natural_Keyword STARTING = new Natural_Keyword("STARTING");
	public @S(50) Natural_Keyword FROM = new Natural_Keyword("FROM");
	public @S(60) Natural_Literal literal;
	public @S(70) TokenList<Natural_Statement> statements;
	public @S(80) Natural_Keyword ENDHISTOGRAM = new Natural_Keyword("END-HISTOGRAM");
}
