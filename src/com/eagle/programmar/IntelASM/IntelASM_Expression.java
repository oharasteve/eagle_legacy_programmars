// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

package com.eagle.programmar.IntelASM;

import com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Literal;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class IntelASM_Expression extends TokenChooser
{
	public @CHOICE IntelASM_Register register;
	public @CHOICE IntelASM_Identifier_Reference var;
	public @CHOICE IntelASM_Number number;
	public @CHOICE IntelASM_Literal literal;
	
	public @CHOICE static class IntelASM_Brackets extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) IntelASM_Register register;
		public @S(30) PunctuationRightBracket rightBracket;
	}
}
