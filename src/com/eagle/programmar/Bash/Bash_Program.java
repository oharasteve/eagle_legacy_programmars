// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2014

package com.eagle.programmar.Bash;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.Bash.Commands.Bash_ExportCommand;
import com.eagle.programmar.Bash.Terminals.Bash_Comment;
import com.eagle.programmar.Bash.Terminals.Bash_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;


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
	
	public @S(10) @OPT TokenList<Bash_ElementLine> elements;
	
	public static class Bash_ElementLine extends TokenSequence
	{
		public @S(10) Bash_Element element;
		public @S(20) Bash_EndOfLine eoln;
		
		public static class Bash_Element extends TokenChooser
		{
			public @CHOICE Bash_Comment comment;
			public @CHOICE Bash_ExportCommand exportCommand;
		}
	}
}
