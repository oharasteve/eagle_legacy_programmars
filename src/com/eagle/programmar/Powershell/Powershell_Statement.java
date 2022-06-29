// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Statements.Powershell_AssignmentStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_BashCommand;
import com.eagle.programmar.Powershell.Statements.Powershell_BreakStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_ForEachStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_ForStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_FunctionStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_IfStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_ReturnStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_SourceStatement;
import com.eagle.programmar.Powershell.Statements.Powershell_WriteStatement;
import com.eagle.programmar.Powershell.Terminals.Powershell_Comment;
import com.eagle.tokens.TokenChooser;

public class Powershell_Statement extends TokenChooser
{
	public @CHOICE Powershell_Comment comment;
	public @CHOICE Powershell_BashCommand bashCommand;
	public @CHOICE Powershell_BreakStatement breakStatement;
	public @CHOICE Powershell_IfStatement ifStatement;
	public @CHOICE Powershell_ForEachStatement foreachStatement;
	public @CHOICE Powershell_ForStatement forStatement;
	public @CHOICE Powershell_FunctionStatement functionDefinition;
	public @CHOICE Powershell_ReturnStatement returnStatement;
	public @CHOICE Powershell_SourceStatement sourceStatement;
	public @CHOICE Powershell_WriteStatement writeStatement;

	public @LAST Powershell_AssignmentStatement assignmentStatement;
}
