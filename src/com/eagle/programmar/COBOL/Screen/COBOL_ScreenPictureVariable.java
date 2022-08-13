// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Screen;

import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class COBOL_ScreenPictureVariable extends TokenSequence
{
	public @S(10) COBOL_KeywordChoice PIC = new COBOL_KeywordChoice("PIC", "PICTURE");
	public @S(20) COBOL_Keyword X = new COBOL_Keyword("X");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) COBOL_Expression size;
	public @S(50) PunctuationRightParen rightParen;
}