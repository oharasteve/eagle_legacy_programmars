// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 19, 2025

package com.eagle.programmar.Javascript.Statements;

import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Javascript.Javascript_Element;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Type;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_ForEachStatement extends TokenSequence
{
	public @S(10) @DOC("js_loop_for.asp") Javascript_Keyword FOR = new Javascript_Keyword("for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Javascript_ForCollectionStatement forCollection;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT TokenList<Javascript_Comment> comments;
	public @S(60) Javascript_Element action;

	private @SKIP ForLoopMetrics _metrics = null;

	public static class Javascript_ForCollectionStatement extends TokenSequence
	{
		public @S(10) @OPT Javascript_Type varType;
		public @S(20) @OPT Javascript_Variable forVar; // The Javascript_Type steals it ...
		public @S(30) @OPT Javascript_ForVariables forVars;
		public @S(40) Javascript_InOrColon inOrColon;
		public @S(50) Javascript_Expression collection;

		public static class Javascript_ForVariables extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) SeparatedList<Javascript_Variable_Definition, PunctuationComma> vars;
			public @S(30) PunctuationRightBracket rightBracket;
		}

		public static class Javascript_InOrColon extends TokenChooser
		{
			public @CHOICE PunctuationColon XXcolon;
			public @CHOICE Javascript_KeywordChoice XXIN = new Javascript_KeywordChoice("in", "of");
		}
	}
}
