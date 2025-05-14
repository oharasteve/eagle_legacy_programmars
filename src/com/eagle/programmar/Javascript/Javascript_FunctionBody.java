// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Javascript_FunctionBody extends TokenSequence
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT TokenList<Javascript_StatementOrComment> statements;
	public @S(30) PunctuationRightBrace rightBrace;
}