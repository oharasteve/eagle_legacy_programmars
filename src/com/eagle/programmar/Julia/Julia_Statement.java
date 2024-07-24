// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia;

import com.eagle.programmar.Julia.Julia_Program.Julia_CommentEoln;
import com.eagle.programmar.Julia.Statements.Julia_Assignment;
import com.eagle.programmar.Julia.Statements.Julia_BlockStatement;
import com.eagle.programmar.Julia.Statements.Julia_BreakStatement;
import com.eagle.programmar.Julia.Statements.Julia_Data;
import com.eagle.programmar.Julia.Statements.Julia_ExpressionStatement;
import com.eagle.programmar.Julia.Statements.Julia_ForStatement;
import com.eagle.programmar.Julia.Statements.Julia_Function;
import com.eagle.programmar.Julia.Statements.Julia_IfStatement;
import com.eagle.programmar.Julia.Statements.Julia_PrintlnStatement;
import com.eagle.programmar.Julia.Statements.Julia_ReturnStatement;
import com.eagle.tokens.TokenChooser;

public class Julia_Statement extends TokenChooser
{
	public @CHOICE Julia_Assignment assignment;
	public @CHOICE Julia_BlockStatement blockStatement;
	public @CHOICE Julia_BreakStatement breakStatement;
	public @CHOICE Julia_CommentEoln comment;
	public @CHOICE Julia_Data data;
	public @CHOICE Julia_ForStatement forStatement;
	public @CHOICE Julia_Function function;
	public @CHOICE Julia_IfStatement ifStatement;
	public @CHOICE Julia_PrintlnStatement putsStatement;
	public @CHOICE Julia_ReturnStatement returnStatement;
	
	public @LAST Julia_ExpressionStatement expressionStatement;
}
