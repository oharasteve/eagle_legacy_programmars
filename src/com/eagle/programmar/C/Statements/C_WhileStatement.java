// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Statement;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_WhileStatement extends TokenSequence
{
	public @S(10) @DOC("#The-while-Statement") C_Keyword WHILE = new C_Keyword("while");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Expression condition;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT C_Comment comment;
	public @S(60) C_Statement whileStatement;
}
