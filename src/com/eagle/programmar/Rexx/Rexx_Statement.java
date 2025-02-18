// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx;

import com.eagle.programmar.Rexx.Statements.Rexx_AssignmentStatement;
import com.eagle.programmar.Rexx.Statements.Rexx_CallStatement;
import com.eagle.programmar.Rexx.Statements.Rexx_DoStatement;
import com.eagle.programmar.Rexx.Statements.Rexx_ExitStatement;
import com.eagle.programmar.Rexx.Statements.Rexx_IfStatement;
import com.eagle.programmar.Rexx.Statements.Rexx_LeaveStatement;
import com.eagle.programmar.Rexx.Statements.Rexx_ReturnStatement;
import com.eagle.programmar.Rexx.Statements.Rexx_SayStatement;
import com.eagle.programmar.Rexx.Terminals.Rexx_Comment;
import com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Rexx_Statement extends TokenSequence implements AbstractStatement
{
	public @S(10) Rexx_BaseStatement baseStatement;
	public @S(20) @OPT Rexx_Comment comment;
	public @S(30) Rexx_EndOfLine eoln;

	public static class Rexx_BaseStatement extends TokenChooser
	{
		public @CHOICE Rexx_Comment XXcomment;
	
		public @CHOICE Rexx_AssignmentStatement XXassignmentStatement;
		public @CHOICE Rexx_CallStatement XXcallStatement;
		public @CHOICE Rexx_DoStatement XXdoStatement;
		public @CHOICE Rexx_ExitStatement XXexitStatement;
		public @CHOICE Rexx_IfStatement XXifStatement;
		public @CHOICE Rexx_LeaveStatement XXleaveStatement;
		public @CHOICE Rexx_ReturnStatement XXreturnStatement;
		public @CHOICE Rexx_SayStatement XXsayStatement;
	}
}
