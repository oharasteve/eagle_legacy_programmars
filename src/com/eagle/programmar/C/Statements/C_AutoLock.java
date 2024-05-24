// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 2, 2022

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_AutoLock extends TokenSequence
{
	public @S(10) @OPT TokenList<C_Comment> comments;
	public @S(20) @OPT C_AutoLockMutex mutex;
	public @S(30) C_Keyword AUTOLOCK = new C_Keyword("AutoLock");
	public @S(40) C_Identifier_Reference id;
	public @S(50) PunctuationLeftParen leftParen;
	public @S(60) C_Expression expr;
	public @S(70) PunctuationRightParen rightParen;
	public @S(80) PunctuationSemicolon semicolon;

	public static class C_AutoLockMutex extends TokenSequence
	{
		public @S(10) C_Keyword MUTEX = new C_Keyword("Mutex");
		public @S(20) C_Punctuation colonColon = new C_Punctuation("::");
	}
}