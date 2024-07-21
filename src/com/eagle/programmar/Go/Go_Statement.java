// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go;

import com.eagle.programmar.Go.Statements.Go_BlockStatement;
import com.eagle.programmar.Go.Statements.Go_BreakStatement;
import com.eagle.programmar.Go.Statements.Go_ConstBlock;
import com.eagle.programmar.Go.Statements.Go_ExpressionStatement;
import com.eagle.programmar.Go.Statements.Go_ForStatement;
import com.eagle.programmar.Go.Statements.Go_IfStatement;
import com.eagle.programmar.Go.Statements.Go_ReturnStatement;
import com.eagle.programmar.Go.Statements.Go_SwitchStatement;
import com.eagle.programmar.Go.Statements.Go_TypeDefinition;
import com.eagle.programmar.Go.Statements.Go_VarStatement;
import com.eagle.tokens.TokenChooser;

public class Go_Statement extends TokenChooser
{
	public @CHOICE Go_BlockStatement blockStatement;
	public @CHOICE Go_BreakStatement breakStatement;
	public @CHOICE Go_CommentEoln comment;
	public @CHOICE Go_ConstBlock constBlock;
	public @CHOICE Go_ExpressionStatement expressionStatement;
	public @CHOICE Go_ForStatement forStatement;
	public @CHOICE Go_IfStatement ifStatement;
	public @CHOICE Go_ReturnStatement returnStatement;
	public @CHOICE Go_SwitchStatement switchStatement;
	public @CHOICE Go_TypeDefinition typeDefinition;
	public @CHOICE Go_VarStatement varStatement;
}
