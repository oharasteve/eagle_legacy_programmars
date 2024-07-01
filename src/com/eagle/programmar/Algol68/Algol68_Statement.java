// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68;

import com.eagle.programmar.Algol68.Statements.Algol68_Assignment;
import com.eagle.programmar.Algol68.Statements.Algol68_Data;
import com.eagle.programmar.Algol68.Statements.Algol68_ExpressionStatement;
import com.eagle.programmar.Algol68.Statements.Algol68_ForStatement;
import com.eagle.programmar.Algol68.Statements.Algol68_IfStatement;
import com.eagle.programmar.Algol68.Statements.Algol68_PrintStatement;
import com.eagle.programmar.Algol68.Statements.Algol68_PrintfStatement;
import com.eagle.programmar.Algol68.Statements.Algol68_Procedure;
import com.eagle.programmar.Algol68.Terminals.Algol68_Comment;
import com.eagle.tokens.TokenChooser;

public class Algol68_Statement extends TokenChooser
{
	public @CHOICE Algol68_Comment comment;
	public @CHOICE Algol68_Data data;
	public @CHOICE Algol68_ForStatement forStatement;
	public @CHOICE Algol68_IfStatement ifStatement;
	public @CHOICE Algol68_PrintStatement printStatement;
	public @CHOICE Algol68_PrintfStatement printfStatement;
	public @CHOICE Algol68_Procedure procedure;

	public @LAST Algol68_Assignment assignment;
	public @LAST Algol68_ExpressionStatement expressionStatement;
}
