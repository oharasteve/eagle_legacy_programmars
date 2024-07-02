// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 31, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_DoStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @NEWLINE @DOC("statements.html#14.13") CSharp_Keyword DO = new CSharp_Keyword("do");
	public @S(20) CSharp_Statement doStatement;
	public @S(30) CSharp_Keyword WHILE = new CSharp_Keyword("while");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) CSharp_Expression condition;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) @NOSPACE PunctuationSemicolon semicolon;
}
