// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP;

import com.eagle.core.AbstractLanguage;
import com.eagle.tokens.TokenList;

public class JavaP_Program extends AbstractLanguage
{
	public static final String JAVAP = "JavaP";

	public JavaP_Program()
	{
		super(JAVAP, new JavaP_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) TokenList<JavaP_Statement> statements;
}
