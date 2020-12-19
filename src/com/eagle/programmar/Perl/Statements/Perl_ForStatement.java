// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_ForStatement extends TokenSequence
{
	public @S(10) @DOC("control-structures.for.php") Perl_Keyword FOR = new Perl_Keyword("for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT Perl_Expression initExpr;
	public @S(40) @OPT PunctuationSemicolon semicolon1;
	public @S(50) @OPT Perl_Expression testExpr;
	public @S(60) @OPT PunctuationSemicolon semicolon2;
	public @S(70) @OPT Perl_Expression incrExpr;
	public @S(80) PunctuationRightParen rightParen;
	public @S(90) Perl_Statement stmt;
}
