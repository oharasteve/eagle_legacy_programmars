// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 9, 2011

package com.eagle.programmar.Perl;

import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleLanguageLookup;
import com.eagle.programmar.Perl.Perl_Statement.Perl_SimpleStatement.Perl_StatementOrComment;
import com.eagle.tokens.TokenList;

public class Perl_Program extends EagleLanguage
{
	public static final String NAME = "Perl";
	
	static {
		EagleLanguageLookup.addLanguage(NAME, Perl_Program.class);
	}

	public Perl_Program()
	{
		super(NAME, new Perl_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://perldoc.perl.org/";
	}
	
	public static final String[] MODIFIERS = new String[] {
		"abstract",
		"const",
		"final",
		"private",
		"protected",
		"public",
		"static",
		"var"
	}; 

	public TokenList<Perl_StatementOrComment> statements;
}
