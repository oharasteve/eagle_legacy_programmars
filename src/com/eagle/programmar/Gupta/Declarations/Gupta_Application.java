// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Declarations;

import com.eagle.programmar.Gupta.Gupta_Declaration;
import com.eagle.programmar.Gupta.Terminals.Gupta_CommentToEndOfLine;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;

public class Gupta_Application extends Gupta_Declaration
{
	public @S(10) Gupta_Keyword Application = new Gupta_Keyword("Application");
	public @S(20) Gupta_Keyword description = new Gupta_Keyword("description");
	public @S(30) Gupta_CommentToEndOfLine comment;

	public @S(40) Gupta_Libraries libraries;
	public @S(50) Gupta_Global_Declarations globalDeclarations;
}
