// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go;

import com.eagle.programmar.Go.Statements.Go_BlockStatement;
import com.eagle.programmar.Go.Statements.Go_BreakStatement;
import com.eagle.programmar.Go.Statements.Go_ConstBlock;
import com.eagle.programmar.Go.Statements.Go_ExpressionStatement;
import com.eagle.programmar.Go.Statements.Go_FmtPrintfStatement;
import com.eagle.programmar.Go.Statements.Go_ForStatement;
import com.eagle.programmar.Go.Statements.Go_IfStatement;
import com.eagle.programmar.Go.Statements.Go_ReturnStatement;
import com.eagle.programmar.Go.Statements.Go_SwitchStatement;
import com.eagle.programmar.Go.Statements.Go_TypeDefinition;
import com.eagle.programmar.Go.Statements.Go_VarStatement;
import com.eagle.programmar.Go.Statements.Go_WhileStatement;
import com.eagle.tokens.TokenChooser;

public class Go_Statement extends TokenChooser
{
	public @CHOICE Go_BlockStatement XXblockStatement;
	public @CHOICE Go_BreakStatement XXbreakStatement;
	public @CHOICE Go_CommentEoln XXcomment;
	public @CHOICE Go_ConstBlock XXconstBlock;
	public @CHOICE Go_FmtPrintfStatement XXfmtPrintfStatement;
	public @CHOICE Go_ForStatement XXforStatement;
	public @CHOICE Go_IfStatement XXifStatement;
	public @CHOICE Go_ReturnStatement XXreturnStatement;
	public @CHOICE Go_SwitchStatement XXswitchStatement;
	public @CHOICE Go_TypeDefinition XXtypeDefinition;
	public @CHOICE Go_VarStatement XXvarStatement;
	public @CHOICE Go_WhileStatement XXwhileStatement;

	public @LAST Go_ExpressionStatement XXexpressionStatement;
}
