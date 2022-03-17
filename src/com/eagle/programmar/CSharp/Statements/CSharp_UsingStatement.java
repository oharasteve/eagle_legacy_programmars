// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 5, 2014

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Data.CSharp_DataBeforeSemicolon;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_UsingStatement extends TokenSequence
{
	public @S(10) @NEWLINE CSharp_Keyword USING = new CSharp_Keyword("using");
	public @S(20) @OPT CSharp_Keyword STATIC = new CSharp_Keyword("static");
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @NOSPACE CSharp_DataBeforeSemicolon declaration;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;
	public @S(60) CSharp_Statement whileStatement;
}
