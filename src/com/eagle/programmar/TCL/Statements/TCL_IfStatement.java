// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Statements;

import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Statement;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.programmar.TCL.Terminals.TCL_PunctuationChoice;
import com.eagle.tokens.TokenSequence;

public class TCL_IfStatement extends TokenSequence
{
	public @S(10) TCL_Keyword IF = new TCL_Keyword("if");
	public @S(20) TCL_PunctuationChoice left = new TCL_PunctuationChoice("{", "(");
	public @S(30) TCL_Expression condition;
	public @S(40) TCL_PunctuationChoice right = new TCL_PunctuationChoice("}", ")");
	public @S(50) TCL_Statement stmt;
	public @S(60) @OPT TCL_ElseClause elseClause;
	
	public static class TCL_ElseClause extends TokenSequence
	{
		public @S(10) TCL_Keyword ELSE = new TCL_Keyword("else");
		public @S(20) TCL_Statement stmt;
	}
}
