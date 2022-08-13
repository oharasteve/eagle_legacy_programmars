// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.C.Types;

import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class C_TypeUserDefined extends TokenSequence
{
	public @S(10) @OPT C_Keyword STRUCT = new C_Keyword("struct");
	public @S(20) C_Identifier_Reference typeName;
	public @S(30) @OPT TokenList<C_TypeStar> stars;
}