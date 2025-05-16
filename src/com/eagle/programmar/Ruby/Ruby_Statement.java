// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby;

import com.eagle.programmar.Ruby.Ruby_Program.Ruby_CommentEoln;
import com.eagle.programmar.Ruby.Statements.Ruby_Assignment;
import com.eagle.programmar.Ruby.Statements.Ruby_BlockStatement;
import com.eagle.programmar.Ruby.Statements.Ruby_BreakStatement;
import com.eagle.programmar.Ruby.Statements.Ruby_Data;
import com.eagle.programmar.Ruby.Statements.Ruby_ExpressionStatement;
import com.eagle.programmar.Ruby.Statements.Ruby_ForStatement;
import com.eagle.programmar.Ruby.Statements.Ruby_Function;
import com.eagle.programmar.Ruby.Statements.Ruby_IfStatement;
import com.eagle.programmar.Ruby.Statements.Ruby_PutsStatement;
import com.eagle.programmar.Ruby.Statements.Ruby_ReturnStatement;
import com.eagle.programmar.Ruby.Statements.Ruby_WhileStatement;
import com.eagle.tokens.TokenChooser;

public class Ruby_Statement extends TokenChooser
{
	public @CHOICE Ruby_Assignment XXassignment;
	public @CHOICE Ruby_BlockStatement XXblockStatement;
	public @CHOICE Ruby_BreakStatement XXbreakStatement;
	public @CHOICE Ruby_CommentEoln XXcomment;
	public @CHOICE Ruby_Data XXdata;
	public @CHOICE Ruby_ForStatement XXforStatement;
	public @CHOICE Ruby_Function XXfunction;
	public @CHOICE Ruby_IfStatement XXifStatement;
	public @CHOICE Ruby_PutsStatement XXputsStatement;
	public @CHOICE Ruby_ReturnStatement XXreturnStatement;
	public @CHOICE Ruby_WhileStatement XXwhileStatement;
	
	public @LAST Ruby_ExpressionStatement XXexpressionStatement;
}
