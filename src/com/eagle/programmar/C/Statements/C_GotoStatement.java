// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 10, 2022

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.Symbols.C_Label_Reference;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_GotoStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("#The-goto-Statement") C_Keyword GOTO = new C_Keyword("goto");
	public @S(20) @OPT C_Label_Reference label;
	public @S(30) PunctuationSemicolon semicolon;
}
