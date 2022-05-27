// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Statement;
import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class VB_ForStatement extends TokenSequence
{
	public @S(10) @DOC("5z06z1kb.aspx") VB_Keyword FOR = new VB_Keyword("for");
	public @S(20) VB_Identifier_Reference var;
	public @S(30) PunctuationEquals equals;
	public @S(40) VB_Expression from;
	public @S(50) VB_Keyword TO = new VB_Keyword("to");
	public @S(60) VB_Expression to;
	public @S(70) @OPT VB_ForStep step;
	public @S(80) VB_EndOfLine eoln;
	public @S(90) TokenList<VB_Statement> action;
	public @S(100) VB_Keyword NEXT = new VB_Keyword("next");
	public @S(110) @OPT VB_Identifier_Reference var2;
	
	public static class VB_ForStep extends TokenSequence
	{
		public @S(10) VB_Keyword STEP = new VB_Keyword("step");
		public @S(20) VB_Expression step;
	}
}
