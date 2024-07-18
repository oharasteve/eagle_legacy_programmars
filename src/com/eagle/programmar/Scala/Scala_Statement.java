// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala;

import com.eagle.programmar.Scala.Statements.Scala_Assignment;
import com.eagle.programmar.Scala.Statements.Scala_BlockStatement;
import com.eagle.programmar.Scala.Statements.Scala_BreakStatement;
import com.eagle.programmar.Scala.Statements.Scala_BreakableStatement;
import com.eagle.programmar.Scala.Statements.Scala_ExpressionStatement;
import com.eagle.programmar.Scala.Statements.Scala_ForStatement;
import com.eagle.programmar.Scala.Statements.Scala_Function;
import com.eagle.programmar.Scala.Statements.Scala_IfStatement;
import com.eagle.programmar.Scala.Statements.Scala_ReturnStatement;
import com.eagle.programmar.Scala.Statements.Scala_Val;
import com.eagle.programmar.Scala.Statements.Scala_Var;
import com.eagle.tokens.TokenChooser;

public class Scala_Statement extends TokenChooser
{
	public @CHOICE Scala_Assignment assignment;
	public @CHOICE Scala_BlockStatement blockStatement;
	public @CHOICE Scala_BreakStatement breakStatement;
	public @CHOICE Scala_BreakableStatement breakableStatement;
	public @CHOICE Scala_CommentEoln comment;
	public @CHOICE Scala_Var var;
	public @CHOICE Scala_Val val;
	public @CHOICE Scala_ForStatement forStatement;
	public @CHOICE Scala_Function function;
	public @CHOICE Scala_IfStatement ifStatement;
	public @CHOICE Scala_ReturnStatement returnStatement;
	
	public @LAST Scala_ExpressionStatement expressionStatement;
}
