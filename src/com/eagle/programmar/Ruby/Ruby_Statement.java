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
import com.eagle.tokens.TokenChooser;

public class Ruby_Statement extends TokenChooser
{
	public @CHOICE Ruby_Assignment assignment;
	public @CHOICE Ruby_BlockStatement blockStatement;
	public @CHOICE Ruby_BreakStatement breakStatement;
	public @CHOICE Ruby_CommentEoln comment;
	public @CHOICE Ruby_Data data;
	public @CHOICE Ruby_ForStatement forStatement;
	public @CHOICE Ruby_Function function;
	public @CHOICE Ruby_IfStatement ifStatement;
	public @CHOICE Ruby_PutsStatement putsStatement;
	public @CHOICE Ruby_ReturnStatement returnStatement;
	
	public @LAST Ruby_ExpressionStatement expressionStatement;
}
