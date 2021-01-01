// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro;

import com.eagle.programmar.CMacro.Statements.CMacro_Define_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Error_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_IfDef_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_IfDef_Statement.CMacro_IfDefCPlusPlus;
import com.eagle.programmar.CMacro.Statements.CMacro_If_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Include_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_LineNumber_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Pragma_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Undef_Statement;
import com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class CMacro_StatementOrComment extends TokenSequence
{
	public @S(10) @OPT CMacro_EndOfLine eoln1;
	public @S(20) CMacro_StmtBody stmt;
	public @S(30) @OPT CMacro_Comment comment;
	public @S(40) CMacro_EndOfLine endOfLine;

	public static class CMacro_StmtBody extends TokenChooser
	{
		public @FIRST CMacro_IfDefCPlusPlus ifdefCPlusPlus;
		
		public @CHOICE CMacro_Define_Statement defineStatement;
		public @CHOICE CMacro_Error_Statement errorStatement;
		public @CHOICE CMacro_If_Statement ifStatement;
		public @CHOICE CMacro_IfDef_Statement ifdefStatement;
		public @CHOICE CMacro_Include_Statement includeStatement;
		public @CHOICE CMacro_LineNumber_Statement lineNumberStatement;
		public @CHOICE CMacro_Pragma_Statement pragmaStatement;
		public @CHOICE CMacro_Undef_Statement undefStatement;
	}
}
