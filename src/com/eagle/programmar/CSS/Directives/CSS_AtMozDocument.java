// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2022

package com.eagle.programmar.CSS.Directives;

import com.eagle.programmar.CSS.CSS_Program.CSS_Body;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSS_AtMozDocument extends TokenSequence
{
	public @S(10) CSS_Punctuation at = new CSS_Punctuation('@');
	public @S(20) CSS_Keyword MOZDOCUMENT = new CSS_Keyword("-moz-document");
	public @S(30) CSS_Keyword URLPREFIX = new CSS_Keyword("url-prefix");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) CSS_Literal literal;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) PunctuationLeftBrace leftBrace;
	public @S(80) TokenList<CSS_Body> bodies;
	public @S(90) PunctuationRightBrace rightBrace;
}
