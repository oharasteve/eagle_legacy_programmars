// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.C;

import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.TokenChooser;

public class C_DataModifiers extends TokenChooser
{
	public @CHOICE C_KeywordChoice scope = new C_KeywordChoice(C_Program.getModifiers());
	public @CHOICE C_Declaration declaration;
}
