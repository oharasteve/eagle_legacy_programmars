// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine;
import com.eagle.tokens.TokenList;

public class MSSolution_Program extends AbstractLanguage
{
	public static final String MSSOLUTION = "MSSolution";

	public MSSolution_Program()
	{
		super(MSSOLUTION, new MSSolution_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) @OPT MSSolution_EndOfLine eoln;
	public @S(20) MSSolution_Header header;
	public @S(30) TokenList<MSSolution_Project> projects;
	public @S(40) MSSolution_Global global;
}
