// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Expression;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Terminals.Natural_EditMask;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Natural_MoveStatement extends TokenSequence
{
	public @S(10) @DOC("sm/move.htm") Natural_Keyword MOVE = new Natural_Keyword("MOVE");
	public @S(20) @OPT Natural_Keyword EDITED = new Natural_Keyword("EDITED");
	public @S(30) Natural_Expression expr;
	public @S(40) Natural_Keyword TO = new Natural_Keyword("TO");
	public @S(50) TokenList<Natural_Variable> variables;
	public @S(60) @OPT Natural_MoveMask mask;

	public static class Natural_MoveMask extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Natural_Keyword EM = new Natural_Keyword("EM");
		public @S(30) PunctuationEquals equals;
		public @S(40) Natural_EditMask mask;
		public @S(50) PunctuationRightParen rightParen;
	}
}
