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
import com.eagle.programmar.Algol68.Statements.Algol68_WhileStatement;
import com.eagle.programmar.Algol68.Terminals.Algol68_Comment;
import com.eagle.tokens.TokenChooser;

public class Algol68_Statement extends TokenChooser
{
	public @CHOICE Algol68_Comment XXcomment;
	public @CHOICE Algol68_Data XXdata;
	public @CHOICE Algol68_ForStatement XXforStatement;
	public @CHOICE Algol68_IfStatement XXifStatement;
	public @CHOICE Algol68_PrintStatement XXprintStatement;
	public @CHOICE Algol68_PrintfStatement XXprintfStatement;
	public @CHOICE Algol68_Procedure XXprocedure;
	public @CHOICE Algol68_WhileStatement XXwhileStatement;

	public @LAST Algol68_Assignment XXassignment;
	public @LAST Algol68_ExpressionStatement XXexpressionStatement;
}
