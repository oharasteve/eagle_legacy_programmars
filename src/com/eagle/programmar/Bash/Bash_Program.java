// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2014

package com.eagle.programmar.Bash;

import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleLanguageLookup;


public class Bash_Program extends EagleLanguage
{
	public static final String NAME = "Bash";
	
	static {
		EagleLanguageLookup.addLanguage(NAME, Bash_Program.class);
		EagleLanguageLookup.setLanguageSuffix(".awk", NAME);
	}

	public Bash_Program()
	{
		super(NAME, new Bash_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}
	
	// Add body
}
