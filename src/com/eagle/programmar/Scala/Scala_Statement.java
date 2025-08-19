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
import com.eagle.programmar.Scala.Statements.Scala_PrintLnStatement;
import com.eagle.programmar.Scala.Statements.Scala_ReturnStatement;
import com.eagle.programmar.Scala.Statements.Scala_ValStatement;
import com.eagle.programmar.Scala.Statements.Scala_VarStatement;
import com.eagle.programmar.Scala.Statements.Scala_WhileStatement;
import com.eagle.tokens.TokenChooser;

public class Scala_Statement extends TokenChooser
{
	public @CHOICE Scala_Assignment XXassignment;
	public @CHOICE Scala_BlockStatement XXblockStatement;
	public @CHOICE Scala_BreakStatement XXbreakStatement;
	public @CHOICE Scala_BreakableStatement XXbreakableStatement;
	public @CHOICE Scala_CommentEoln XXcomment;
	public @CHOICE Scala_VarStatement XXvar;
	public @CHOICE Scala_ValStatement XXval;
	public @CHOICE Scala_ForStatement XXforStatement;
	public @CHOICE Scala_Function XXfunction;
	public @CHOICE Scala_IfStatement XXifStatement;
	public @CHOICE Scala_PrintLnStatement XXprintStatement;
	public @CHOICE Scala_ReturnStatement XXreturnStatement;
	public @CHOICE Scala_WhileStatement XXwhileStatement;
	
	public @LAST Scala_ExpressionStatement XXexpressionStatement;
}
