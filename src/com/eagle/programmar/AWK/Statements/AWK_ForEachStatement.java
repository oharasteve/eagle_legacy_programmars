// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 22, 2025

package com.eagle.programmar.AWK.Statements;

import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.AWK.AWK_Action;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Statements.AWK_Statement;
import com.eagle.programmar.AWK.AWK_Variable;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_ForEachStatement extends TokenSequence
{
	public @S(10) @DOC("#For-Statement") AWK_Keyword FOR = new AWK_Keyword("for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_Variable var;
	public @S(40) AWK_Keyword IN = new AWK_Keyword("in");
	public @S(50) AWK_Expression value;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) @OPT AWK_EndOfLine eoln;
	public @S(80) AWK_ForEachBlock block;

	private @SKIP ForLoopMetrics _metrics = null;

	public static class AWK_ForEachBlock extends TokenChooser
	{
		public @CHOICE AWK_Statement XXstmt;
		public @CHOICE AWK_Action XXactions;
	}
}
