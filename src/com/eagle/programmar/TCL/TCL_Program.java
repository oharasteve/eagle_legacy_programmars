// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.core.EagleLanguage;
import com.eagle.tokens.TokenList;

public class TCL_Program extends EagleLanguage
{
	public static final String TCL = "TCL";
	
	public TCL_Program()
	{
		super(TCL, new TCL_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "https://www.tcl.tk/man/tcl8.7/";
	}
	
	public @S(10) TokenList<TCL_Statement> statements;
}	
