// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.C.Types;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class C_TypeLongLong extends TokenSequence
{
	public @S(10) @OPT C_KeywordChoice UNSIGNED = new C_KeywordChoice("signed", "unsigned", "__signed__");
	public @S(20) C_Keyword LONG1 = new C_Keyword("long");
	public @S(30) C_Keyword LONG2 = new C_Keyword("long");
}
