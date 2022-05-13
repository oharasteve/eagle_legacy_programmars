// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 10, 2022

package com.eagle.programmar.ObjectiveC;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CPlus.Symbols.CPlus_Class_Definition;
import com.eagle.tokens.TokenSequence;

public class ObjectiveC_Protocol extends TokenSequence
{
	public @S(10) C_Punctuation at = new C_Punctuation("@");
	public @S(20) C_Keyword PROTOCOL = new C_Keyword("protocol");
	public @S(30) CPlus_Class_Definition name;
}
