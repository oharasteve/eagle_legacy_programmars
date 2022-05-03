// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 2, 2022

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Data.C_RegularData.C_DataInitialValue;
import com.eagle.programmar.C.C_Data.C_RegularData.C_DataModifiers;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.CPlus.CPlus_Method.CPlus_NamespaceQualifier;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CPlus_Data extends TokenSequence
{
	public @S(10) @OPT TokenList<C_DataModifiers> modifiers;
	public @S(20) C_Type ctype;
	public @S(30) @OPT TokenList<CPlus_NamespaceQualifier> namespaces;
	public @S(40) C_Variable_Definition id;
	public @S(60) @OPT C_DataInitialValue initialValue;
	public @S(70) @NOSPACE PunctuationSemicolon semicolon;

}
