// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution;

import com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class MSSolution_Project extends TokenSequence
{
	public @S(10) MSSolution_Keyword PROJECT = new MSSolution_Keyword("Project");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) MSSolution_Literal guid1;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationEquals equals;
	public @S(60) MSSolution_Literal name;
	public @S(70) PunctuationComma comma1;
	public @S(80) MSSolution_Literal projectLocation;
	public @S(90) PunctuationComma comma2;
	public @S(100) MSSolution_Literal guid2;
	public @S(110) MSSolution_EndOfLine eoln1;
	
	public @S(120) MSSolution_ProjectSection projectSection;
	
	public @S(130) MSSolution_Keyword ENDPROJECT = new MSSolution_Keyword("EndProject");
	public @S(140) MSSolution_EndOfLine eoln2;
}
