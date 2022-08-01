// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2022

package com.eagle.programmar.CSS.Directives;

import com.eagle.programmar.CSS.CSS_Program.CSS_Body;
import com.eagle.programmar.CSS.CSS_TagList.CSS_Tag;
import com.eagle.programmar.CSS.CSS_Value;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSS_AtSupports extends TokenSequence
{
	public @S(10) CSS_Punctuation at = new CSS_Punctuation('@');
	public @S(20) CSS_Keyword SUPPORTS = new CSS_Keyword("supports");
	public @S(30) @OPT CSS_Keyword NOT = new CSS_Keyword("not");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) CSS_Tag tag;
	public @S(60) PunctuationColon colon;
	public @S(70) CSS_Value value;
	public @S(80) PunctuationRightParen rightParen;
	public @S(90) PunctuationLeftBrace leftBrace;
	public @S(100) CSS_Body rule;
	public @S(110) PunctuationRightBrace rightBrace;
}
