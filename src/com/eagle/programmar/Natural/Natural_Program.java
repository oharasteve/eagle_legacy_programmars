// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

package com.eagle.programmar.Natural;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenList;

public class Natural_Program extends AbstractLanguage
{
	public static final String NATURAL = "Natural";

	public Natural_Program()
	{
		super(NATURAL, new Natural_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://documentation.softwareag.com/natural/nat827mf/";
	}

	// Components of a Natural Program
	public @S(10) TokenList<Natural_Statement> statements;
	public @S(20) @OPT Natural_Keyword END = new Natural_Keyword("END");
}
