// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Statement;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_AtStatement extends TokenSequence
{
	public @S(10) Natural_Keyword AT = new Natural_Keyword("AT");
	public @S(20) Natural_AtWhat atWhat;

	public static class Natural_AtWhat extends TokenChooser
	{
		public @CHOICE static class Natural_AtEndOfData extends TokenSequence
		{
			public @S(10) @DOC("sm/atenddat.htm") Natural_Keyword END = new Natural_Keyword("END");
			public @S(20) @OPT Natural_Keyword OF = new Natural_Keyword("OF");
			public @S(30) Natural_Keyword DATA = new Natural_Keyword("DATA");
			public @S(40) TokenList<Natural_Statement> statements;
			public @S(50) @OPT Natural_Keyword ENDENDDATA = new Natural_Keyword("END-ENDDATA");
		}

		public @CHOICE static class Natural_AtEndOfPage extends TokenSequence
		{
			public @S(10) @DOC("sm/atendpag.htm") Natural_Keyword END = new Natural_Keyword("END");
			public @S(20) @OPT Natural_Keyword OF = new Natural_Keyword("OF");
			public @S(30) Natural_Keyword PAGE = new Natural_Keyword("PAGE");
			public @S(40) TokenList<Natural_Statement> statements;
			public @S(50) Natural_Keyword ENDENDPAGE = new Natural_Keyword("END-ENDPAGE");
		}

		public @CHOICE static class Natural_AtBreakOfVariable extends TokenSequence
		{
			public @S(10) @DOC("sm/atbreak.htm") Natural_Keyword BREAK = new Natural_Keyword("BREAK");
			public @S(20) @OPT Natural_Keyword OF = new Natural_Keyword("OF");
			public @S(30) Natural_Variable var;
			public @S(40) TokenList<Natural_Statement> statements;
			public @S(50) Natural_Keyword ENDBREAK = new Natural_Keyword("END-BREAK");
		}

		public @CHOICE static class Natural_AtStartOfData extends TokenSequence
		{
			public @S(10) @DOC("sm/atstart.htm") Natural_Keyword START = new Natural_Keyword("START");
			public @S(20) @OPT Natural_Keyword OF = new Natural_Keyword("OF");
			public @S(30) Natural_Keyword DATA = new Natural_Keyword("DATA");
			public @S(40) TokenList<Natural_Statement> statements;
			public @S(50) Natural_Keyword ENDSTART = new Natural_Keyword("END-START");
		}
	}
}
