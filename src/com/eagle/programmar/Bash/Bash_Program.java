// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2014

package com.eagle.programmar.Bash;

import com.eagle.core.EagleLanguage;


public class Bash_Program extends EagleLanguage
{
	public static final String BASH = "Bash";
	
	public Bash_Program()
	{
		super(BASH, new Bash_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}
	
	// Add body
}
