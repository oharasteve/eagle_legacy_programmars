// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Picture;

import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Picture;
import com.eagle.tokens.TokenSequence;

public class COBOL_PictureClause extends TokenSequence
{
	public @S(10) COBOL_KeywordChoice PIC = new COBOL_KeywordChoice("PIC", "PICTURE");
	public @S(20) COBOL_Picture picture;
}