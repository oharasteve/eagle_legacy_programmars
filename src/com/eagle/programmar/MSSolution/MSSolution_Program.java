// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution;

import com.eagle.core.EagleLanguage;
import com.eagle.tokens.TokenList;

public class MSSolution_Program extends EagleLanguage
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
	
	public @S(10) MSSolution_Header header;
	public @S(20) TokenList<MSSolution_Project> projects;
	public @S(30) MSSolution_Global global;
}
