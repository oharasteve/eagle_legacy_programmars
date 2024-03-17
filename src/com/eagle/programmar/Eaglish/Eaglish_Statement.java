// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish;

import com.eagle.programmar.Eaglish.Statements.Eaglish_Add_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Print_Statement;
import com.eagle.tokens.TokenChooser;

public class Eaglish_Statement extends TokenChooser
{
	public @CHOICE Eaglish_Add_Statement addStatement;
	public @CHOICE Eaglish_Print_Statement printStatement;
}
