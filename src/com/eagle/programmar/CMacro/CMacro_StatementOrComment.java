// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CMacro.Statements.CMacro_Define_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Error_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_IfDef_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_IfDef_Statement.CMacro_IfDefCPlusPlus;
import com.eagle.programmar.CMacro.Statements.CMacro_If_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Include_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_LineNumber_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Pragma_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Region_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Undef_Statement;
import com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_StatementOrComment extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT CMacro_EndOfLine eoln1;
	public @S(20) CMacro_StmtBody stmt;
	public @S(30) @OPT TokenList<CMacro_Comment> comments;
	public @S(40) CMacro_EndOfLine endOfLine;

	public static class CMacro_StmtBody extends TokenChooser
	{
		public @FIRST CMacro_IfDefCPlusPlus XXifdefCPlusPlus;

		public @CHOICE CMacro_Define_Statement XXdefineStatement;
		public @CHOICE CMacro_Error_Statement XXerrorStatement;
		public @CHOICE CMacro_If_Statement XXifStatement;
		public @CHOICE CMacro_IfDef_Statement XXifdefStatement;
		public @CHOICE CMacro_Include_Statement XXincludeStatement;
		public @CHOICE CMacro_LineNumber_Statement XXlineNumberStatement;
		public @CHOICE CMacro_Pragma_Statement XXpragmaStatement;
		public @CHOICE CMacro_Region_Statement XXregionStatement;
		public @CHOICE CMacro_Undef_Statement XXundefStatement;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Don't do anything right now ... deal with it later
	}
}
