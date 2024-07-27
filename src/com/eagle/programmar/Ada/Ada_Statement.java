// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada;

import com.eagle.programmar.Ada.Statements.Ada_Assignment;
import com.eagle.programmar.Ada.Statements.Ada_Data;
import com.eagle.programmar.Ada.Statements.Ada_ExitStatement;
import com.eagle.programmar.Ada.Statements.Ada_ExpressionStatement;
import com.eagle.programmar.Ada.Statements.Ada_ForStatement;
import com.eagle.programmar.Ada.Statements.Ada_Function;
import com.eagle.programmar.Ada.Statements.Ada_IfStatement;
import com.eagle.programmar.Ada.Statements.Ada_Procedure;
import com.eagle.programmar.Ada.Statements.Ada_PutIntegerStatement;
import com.eagle.programmar.Ada.Statements.Ada_PutStatement;
import com.eagle.programmar.Ada.Statements.Ada_ReturnStatement;
import com.eagle.programmar.Ada.Statements.Ada_WithUseStatement;
import com.eagle.programmar.Ada.Terminals.Ada_Comment;
import com.eagle.tokens.TokenChooser;

public class Ada_Statement extends TokenChooser
{
	public @CHOICE Ada_Assignment XXassignment;
	public @CHOICE Ada_ExitStatement XXbreakStatement;
	public @CHOICE Ada_Comment XXcomment;
	public @CHOICE Ada_Data XXdata;
	public @CHOICE Ada_ForStatement XXforStatement;
	public @CHOICE Ada_Function XXfunction;
	public @CHOICE Ada_IfStatement XXifStatement;
	public @CHOICE Ada_PutStatement XXputStatement;
	public @CHOICE Ada_PutIntegerStatement XXputIntegerStatement;
	public @CHOICE Ada_Procedure XXprocedure;
	public @CHOICE Ada_ReturnStatement XXreturnStatement;
	public @CHOICE Ada_WithUseStatement XXwithUseStatement;

	public @LAST Ada_ExpressionStatement XXexpressionStatement;
}
