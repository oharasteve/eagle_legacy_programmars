// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Perl_ExpressionList extends TokenSequence
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT TokenList<Perl_Comment> comment;
	public @S(30) Perl_ArgumentList valueList;
	public @S(40) PunctuationRightBrace rightBrace;
}
