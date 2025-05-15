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
import com.eagle.programmar.Julia.Statements.Julia_WhileStatement;
import com.eagle.tokens.TokenChooser;

public class Julia_Statement extends TokenChooser
{
	public @CHOICE Julia_Assignment XXassignment;
	public @CHOICE Julia_BlockStatement XXblockStatement;
	public @CHOICE Julia_BreakStatement XXbreakStatement;
	public @CHOICE Julia_CommentEoln XXcomment;
	public @CHOICE Julia_Data XXdata;
	public @CHOICE Julia_ForStatement XXforStatement;
	public @CHOICE Julia_Function XXfunction;
	public @CHOICE Julia_IfStatement XXifStatement;
	public @CHOICE Julia_PrintlnStatement XXputsStatement;
	public @CHOICE Julia_ReturnStatement XXreturnStatement;
	public @CHOICE Julia_WhileStatement XXwhileStatement;
	
	public @LAST Julia_ExpressionStatement XXexpressionStatement;
}
