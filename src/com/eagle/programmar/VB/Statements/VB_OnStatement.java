// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class VB_OnStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) VB_Keyword ON = new VB_Keyword("on");
	public @S(20) VB_Keyword ERROR = new VB_Keyword("error");
	public @S(30) VB_OnWhat onWhat;

	public static class VB_OnWhat extends TokenChooser
	{
		public @CHOICE static class VB_OnResume extends TokenSequence
		{
			public @S(10) VB_Keyword RESUME = new VB_Keyword("resume");
			public @S(20) VB_Keyword NEXT = new VB_Keyword("next");
		}

		public @CHOICE static class VB_OnGotoZero extends TokenSequence
		{
			public @S(10) VB_Keyword GOTO = new VB_Keyword("goto");
			public @S(20) VB_Number zero;
		}

		public @CHOICE static class VB_OnGotoLabel extends TokenSequence
		{
			public @S(10) VB_Keyword GOTO = new VB_Keyword("goto");
			public @S(20) VB_Identifier_Reference lbl;
		}
	}
}
