// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 14, 2022

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Function;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CPlus.Symbols.CPlus_Class_Definition;
import com.eagle.tokens.TokenSequence;

public class CPlus_Template extends TokenSequence
{
	public @S(10) C_Keyword TEMPLATE = new C_Keyword("template");
	public @S(20) C_Punctuation less = new C_Punctuation("<");
	public @S(30) C_KeywordChoice CLASS = new C_KeywordChoice("class", "typename");
	public @S(40) CPlus_Class_Definition cls;
	public @S(50) C_Punctuation greater = new C_Punctuation(">");
	public @S(60) C_Function func;
}
