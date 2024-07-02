// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_ReturnStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @NEWLINE @OPT CSharp_Keyword YIELD = new CSharp_Keyword("yield");
	public @S(20) @DOC("statements.html#14.17") CSharp_Keyword RETURN = new CSharp_Keyword("return");
	public @S(30) @OPT CSharp_Expression expression;
	public @S(40) @NOSPACE PunctuationSemicolon semicolon;
}
