// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada;

import com.eagle.programmar.Ada.Statements.Ada_Assignment;
import com.eagle.programmar.Ada.Statements.Ada_Data;
import com.eagle.programmar.Ada.Statements.Ada_ExitStatement;
import com.eagle.programmar.Ada.Statements.Ada_ForStatement;
import com.eagle.programmar.Ada.Statements.Ada_Function;
import com.eagle.programmar.Ada.Statements.Ada_FunctionCall;
import com.eagle.programmar.Ada.Statements.Ada_IfStatement;
import com.eagle.programmar.Ada.Statements.Ada_Procedure;
import com.eagle.programmar.Ada.Statements.Ada_PutStatement;
import com.eagle.programmar.Ada.Statements.Ada_ReturnStatement;
import com.eagle.programmar.Ada.Statements.Ada_WithUseStatement;
import com.eagle.programmar.Ada.Terminals.Ada_Comment;
import com.eagle.tokens.TokenChooser;

public class Ada_Statement extends TokenChooser
{
	public @CHOICE Ada_Assignment assignment;
	public @CHOICE Ada_ExitStatement breakStatement;
	public @CHOICE Ada_Comment comment;
	public @CHOICE Ada_Data data;
	public @CHOICE Ada_ForStatement forStatement;
	public @CHOICE Ada_Function function;
	public @CHOICE Ada_FunctionCall functionCall;
	public @CHOICE Ada_IfStatement ifStatement;
	public @CHOICE Ada_PutStatement putsStatement;
	public @CHOICE Ada_Procedure procedure;
	public @CHOICE Ada_ReturnStatement returnStatement;
	public @CHOICE Ada_WithUseStatement withUseStatement;
}
