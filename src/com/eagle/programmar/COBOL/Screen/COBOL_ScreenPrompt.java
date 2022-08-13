// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Screen;

import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenSequence;

public class COBOL_ScreenPrompt extends TokenSequence
{
	public @S(10) COBOL_Keyword PROMPT = new COBOL_Keyword("PROMPT");
	public @S(20) COBOL_Literal literal;
}