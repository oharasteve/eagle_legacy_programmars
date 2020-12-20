// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.Gupta.Declarations.Gupta_Application;

public class Gupta_Program extends EagleLanguage
{
	public static final String GUPTA = "Gupta";
	
	public Gupta_Program()
	{
		super(GUPTA, new Gupta_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "Unknown";
	}

	// Components of a Gupta Program
	public @S(10) Gupta_Application application;
}
