// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 9, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Condition;
import com.eagle.programmar.Natural.Natural_Statement;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_IfStatement extends TokenSequence
{
	public @S(10) @DOC("sm/if.htm") Natural_Keyword IF = new Natural_Keyword("IF");
	public @S(20) Natural_IfWhat ifWhat;
	public @S(30) TokenList<Natural_Statement> statements;
	public @S(40) @OPT Natural_ElseClause elseClause;
	public @S(50) Natural_KeywordChoice endIf = new Natural_KeywordChoice("END-IF", "END-NOREC");

	public static class Natural_IfWhat extends TokenChooser
	{
		public @CHOICE Natural_NoRecordsFound XXnoRecordsFound;
		public @CHOICE Natural_Condition XXcondition;
	}

	public static class Natural_NoRecordsFound extends TokenSequence
	{
		public @S(10) Natural_Keyword NO = new Natural_Keyword("NO");
		public @S(20) Natural_Keyword RECORDS = new Natural_Keyword("RECORDS");
		public @S(30) Natural_Keyword FOUND = new Natural_Keyword("FOUND");
	}

	public static class Natural_ElseClause extends TokenSequence
	{
		public @S(10) Natural_Keyword ELSE = new Natural_Keyword("ELSE");
		public @S(20) TokenList<Natural_Statement> statements;
	}
}
