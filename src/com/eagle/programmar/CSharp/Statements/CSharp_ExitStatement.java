// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2024

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_ExitStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @NEWLINE CSharp_Keyword SYSTEM = new CSharp_Keyword("System");
	public @S(20) @NOSPACE PunctuationPeriod dot1;
	public @S(30) @NOSPACE CSharp_Keyword ENVIRONMENT = new CSharp_Keyword("Environment");
	public @S(40) @NOSPACE PunctuationPeriod dot2;
	public @S(50) @NOSPACE CSharp_Keyword EXIT = new CSharp_Keyword("Exit");
	public @S(60) @NOSPACE PunctuationLeftParen leftParen;
	public @S(70) @NOSPACE @OPT CSharp_Expression expr;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;
	public @S(90) @NOSPACE PunctuationSemicolon semicolon;
}
