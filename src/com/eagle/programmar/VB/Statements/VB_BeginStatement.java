// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Statement;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class VB_BeginStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) VB_Keyword BEGIN = new VB_Keyword("begin");
	public @S(20) VB_EndOfLine eoln;
	public @S(30) TokenList<VB_Statement> stmts;
	public @S(40) VB_Keyword END = new VB_Keyword("end");

}
