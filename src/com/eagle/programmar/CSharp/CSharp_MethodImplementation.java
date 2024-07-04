// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Statements.CSharp_StatementBlock;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_MethodImplementation extends TokenSequence
{
	public @S(10) @OPT TokenList<CSharp_Comment> comment1;
	public @S(20) @NOSPACE CSharp_StatementBlock block;
	public @S(30) @OPT TokenList<CSharp_Comment> comment2;
	public @S(40) @OPT @CURIOUS(value = "Extra semicolon") PunctuationSemicolon semicolon2;
}