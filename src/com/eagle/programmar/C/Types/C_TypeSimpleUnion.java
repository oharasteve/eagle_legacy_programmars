// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.C.Types;

import com.eagle.programmar.C.Symbols.C_Type_Definition;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenSequence;

public class C_TypeSimpleUnion extends TokenSequence
{
	public @S(10) C_Keyword UNION = new C_Keyword("union");
	public @S(20) C_Type_Definition def;
}