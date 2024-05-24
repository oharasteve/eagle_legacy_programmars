// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Declarations;

import com.eagle.programmar.Gupta.Gupta_Declaration;
import com.eagle.programmar.Gupta.Gupta_Variable_Declaration;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
import com.eagle.tokens.TokenList;

public class Gupta_Constants extends Gupta_Declaration
{
	public @S(10) Gupta_Keyword Constants = new Gupta_Keyword("Constants");
	public @S(20) Gupta_System_Constants systemConstants;
	public @S(30) Gupta_User_Constants userConstants;

	public static class Gupta_System_Constants extends Gupta_Declaration
	{
		public @S(10) Gupta_Keyword System = new Gupta_Keyword("System");
	}

	public static class Gupta_User_Constants extends Gupta_Declaration
	{
		public @S(10) Gupta_Keyword User = new Gupta_Keyword("User");
		public @S(20) TokenList<Gupta_Variable_Declaration> constants;
	}
}
