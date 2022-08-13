// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.C.Types;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class C_TypeShortUnsignedInt extends TokenSequence
{
	public @S(10) @OPT C_KeywordChoice UNSIGNED1 = new C_KeywordChoice("signed", "unsigned");
	public @S(20) C_KeywordChoice SHORT = new C_KeywordChoice("long", "short");
	public @S(30) @OPT C_Keyword LONG = new C_Keyword("long");
	public @S(40) @OPT C_KeywordChoice UNSIGNED2 = new C_KeywordChoice("signed", "unsigned");
	public @S(50) C_KeywordChoice INT = new C_KeywordChoice("int", "double");
	public @S(60) @OPT TokenList<C_TypeStar> stars;
}