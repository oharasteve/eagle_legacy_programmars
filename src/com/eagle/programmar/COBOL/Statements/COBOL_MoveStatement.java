// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.Symbols.COBOL_Modifiable_Identifier;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_MoveStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsmove.htm") COBOL_Keyword MOVE = new COBOL_Keyword("MOVE");
	public @S(20) @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
	public @S(30) COBOL_Expression expr;
	public @S(40) COBOL_Keyword TO = new COBOL_Keyword("TO");
	public @S(50) @OPT COBOL_Modifiable_Identifier var;
	public @S(60) @OPT COBOL_Subscript subscript;
	public @S(70) @OPT TokenList<COBOL_MoveMore> more;
	public @S(80) @OPT @CURIOUS("MOVE: Extra comma") PunctuationComma comma;
	
	public static class COBOL_MoveMore extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) @OPT TokenList<COBOL_Comment> comments;
		public @S(30) COBOL_Modifiable_Identifier var;
		public @S(40) @OPT COBOL_Subscript subscript;
	}
}
