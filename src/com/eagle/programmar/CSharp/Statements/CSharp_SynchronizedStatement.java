// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_SynchronizedStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @NEWLINE @DOC("statements.html#14.19") CSharp_Keyword SYNCHRONIZED = new CSharp_Keyword(
			"synchronized");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE CSharp_Expression expr;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) CSharp_Statement syncStatement;
}
