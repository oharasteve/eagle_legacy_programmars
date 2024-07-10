// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import com.eagle.programmar.CMD.CMD_Syntax;
import com.eagle.programmar.CMD.Statements.CMD_FindStr_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Xcopy_Statement;
import com.eagle.programmar.Powershell.Statements.Powershell_AssignmentStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_BeginBlock;
import com.eagle.programmar.Powershell.Statements.Powershell_BreakStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_Command;
import com.eagle.programmar.Powershell.Statements.Powershell_ContinueStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_DoStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_ExitStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_ForEachStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_ForStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_FunctionStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_IfStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_ProcessBlock;
import com.eagle.programmar.Powershell.Statements.Powershell_ReturnStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_StartJob;
import com.eagle.programmar.Powershell.Statements.Powershell_SwitchStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_ThrowStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_TryStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_WhereStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_WhileStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_WriteStatement;
import com.eagle.programmar.Powershell.Terminals.Powershell_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Powershell_Statement extends TokenSequence
{
	public @S(10) Powershell_Element element;
	public @S(20) @OPT Powershell_Comment comment;
	public @S(30) @OPT Powershell_EndOfLine eoln;

	public static class Powershell_Element extends TokenChooser
	{
		public @CHOICE Powershell_BeginBlock beginBlock;
		public @CHOICE Powershell_BreakStatement breakStatement;
		public @CHOICE Powershell_Comment comment;
		public @CHOICE Powershell_ContinueStatement continueStatement;
		public @CHOICE Powershell_Directive directive;
		public @CHOICE Powershell_DoStatement doStatement;
		public @CHOICE Powershell_ExitStatement exitStatement;
		public @CHOICE Powershell_IfStatement ifStatement;
		public @CHOICE Powershell_ForEachStatement foreachStatement;
		public @CHOICE Powershell_ForStatement forStatement;
		public @CHOICE Powershell_FunctionStatement functionDefinition;
		public @CHOICE Powershell_ProcessBlock processBlock;
		public @CHOICE Powershell_ReturnStatement returnStatement;
		public @CHOICE Powershell_StartJob startJob;
		public @CHOICE Powershell_SwitchStatement switchStatement;
		public @CHOICE Powershell_ThrowStatement throwStatement;
		public @CHOICE Powershell_TryStatement tryStatement;
		public @CHOICE Powershell_WhereStatement whereObjectStatement;
		public @CHOICE Powershell_WriteStatement writeStatement;
		public @CHOICE Powershell_WhileStatement whileStatement;

		public @CHOICE @SYNTAX(CMD_Syntax.class) CMD_Xcopy_Statement xcopyStatement;
		public @CHOICE @SYNTAX(CMD_Syntax.class) CMD_FindStr_Statement findstrStatement;

		public @LAST Powershell_Command command;
		public @LAST Powershell_AssignmentStatement assignmentStatement;
	}
}
