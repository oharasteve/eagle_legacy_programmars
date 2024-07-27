// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.COBOL.Screen;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenChooser;

public class COBOL_NumberOrIdentifier extends TokenChooser
{
	public @CHOICE COBOL_Number XXcolor;
	public @CHOICE COBOL_Identifier_Reference XXdataRef;
}