// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import com.eagle.programmar.CMD.CMD_Syntax;
import com.eagle.programmar.CMD.Statements.CMD_FindStr_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Xcopy_Statement;
import com.eagle.programmar.Powershell.Statements.Powershell_BeginBlock;
import com.eagle.programmar.Powershell.Statements.Powershell_BreakStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_Command;
import com.eagle.programmar.Powershell.Statements.Powershell_ContinueStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_DoStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_ExitStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_ExpressionStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_ForEachStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_ForStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_Function;
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
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Powershell_Element extends TokenSequence
{
	public @S(10) Powershell_Statement element;
	public @S(20) @OPT Powershell_Comment comment;
	public @S(30) @OPT Powershell_EndOfLine eoln;

	public static class Powershell_Statement extends TokenChooser
	{
		public @CHOICE Powershell_BeginBlock XXbeginBlock;
		public @CHOICE Powershell_BreakStatement XXbreakStatement;
		public @CHOICE Powershell_Comment XXcomment;
		public @CHOICE Powershell_ContinueStatement XXcontinueStatement;
		public @CHOICE Powershell_Directive XXdirective;
		public @CHOICE Powershell_DoStatement XXdoStatement;
		public @CHOICE Powershell_ExitStatement XXexitStatement;
		public @CHOICE Powershell_IfStatement XXifStatement;
		public @CHOICE Powershell_ForEachStatement XXforeachStatement;
		public @CHOICE Powershell_ForStatement XXforStatement;
		public @CHOICE Powershell_Function XXfunctionDefinition;
		public @CHOICE Powershell_ProcessBlock XXprocessBlock;
		public @CHOICE Powershell_ReturnStatement XXreturnStatement;
		public @CHOICE Powershell_StartJob XXstartJob;
		public @CHOICE Powershell_SwitchStatement XXswitchStatement;
		public @CHOICE Powershell_ThrowStatement XXthrowStatement;
		public @CHOICE Powershell_TryStatement XXtryStatement;
		public @CHOICE Powershell_WhereStatement XXwhereObjectStatement;
		public @CHOICE Powershell_WriteStatement XXwriteStatement;
		public @CHOICE Powershell_WhileStatement XXwhileStatement;
		
		public @CHOICE @SYNTAX(CMD_Syntax.class) CMD_Xcopy_Statement XXxcopyStatement;
		public @CHOICE @SYNTAX(CMD_Syntax.class) CMD_FindStr_Statement XXfindstrStatement;

		public @CHOICE Powershell_Command XXcommand;
		public @LAST Powershell_ExpressionStatement XXexpressionStatement;
	}
}
