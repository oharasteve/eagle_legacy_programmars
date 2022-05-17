// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

package com.eagle.programmar.IntelASM;

import com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Definition;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class IntelASM_Label extends TokenSequence
{
	public @S(10) IntelASM_Label_Definition label;
	public @S(20) PunctuationColon colon;
}
