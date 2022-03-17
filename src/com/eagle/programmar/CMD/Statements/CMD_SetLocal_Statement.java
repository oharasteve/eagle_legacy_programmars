// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMD_SetLocal_Statement extends TokenSequence
{
	public @S(10) @DOC("setlocal.mspx") CMD_Keyword SETLOCAL = new CMD_Keyword("setlocal");
	public @S(20) @OPT TokenList<CMD_SetLocalWhat> setWhat;
	
	public static class CMD_SetLocalWhat extends TokenChooser
	{
		public @CHOICE CMD_Keyword ENABLEEXTENSIONS = new CMD_Keyword("ENABLEEXTENSIONS");
		public @CHOICE CMD_Keyword ENABLEDELAYEDEXPANSION = new CMD_Keyword("ENABLEDELAYEDEXPANSION");
	}
}
