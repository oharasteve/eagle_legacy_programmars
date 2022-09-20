// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import com.eagle.core.EagleLanguage;
import com.eagle.tokens.TokenList;

public class Powershell_Program extends EagleLanguage
{
	public static final String POWERHSELL = "Powershell";
	
	public Powershell_Program()
	{
		super(POWERHSELL, new Powershell_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "https://docs.microsoft.com/en-us/powershell/scripting/lang-spec/";
	}
	
	public @S(10) @OPT Powershell_CmdletBinding cmtletBinding;
	public @S(20) @OPT TokenList<Powershell_CommentEoln> comments;
	public @S(30) @OPT TokenList<Powershell_Statement> statements;
}