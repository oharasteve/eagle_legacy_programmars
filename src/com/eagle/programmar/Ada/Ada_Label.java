// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2026

package com.eagle.programmar.Ada;

import com.eagle.programmar.Ada.Symbols.Ada_Label_Definition;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Ada_Label extends TokenSequence
{
	public @S(10) Ada_Label_Definition label;
	public @S(20) PunctuationColon colon;
}
