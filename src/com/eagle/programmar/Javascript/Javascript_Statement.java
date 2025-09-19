// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Statements.Javascript_BreakStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_ContinueStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_DoStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_DocumentWriteln;
import com.eagle.programmar.Javascript.Statements.Javascript_ExpressionStmt;
import com.eagle.programmar.Javascript.Statements.Javascript_ForStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_IfStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_ImportStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_ReturnStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_StatementBlock;
import com.eagle.programmar.Javascript.Statements.Javascript_SwitchStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_ThrowStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_TryStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_WhileStatement;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_Statement extends TokenChooser
{
	public @FIRST Javascript_StatementBlock XXblock;
	public @CHOICE Javascript_Data XXdata;
	public @CHOICE @CURIOUS("Extra semicolon") PunctuationSemicolon XXsemicolon;

	public @CHOICE Javascript_BreakStatement XXbreakStatement;
	public @CHOICE Javascript_ContinueStatement XXcontinueStatement;
	public @CHOICE Javascript_DoStatement XXdoStatement;
	public @CHOICE Javascript_DocumentWriteln XXdocumentWriteln;
	public @CHOICE Javascript_ForStatement XXforStatement;
	public @CHOICE Javascript_Function XXfunction;
	public @CHOICE Javascript_IfStatement XXifStatement;
	public @CHOICE Javascript_ImportStatement XXimportStatement;
	public @CHOICE Javascript_ReturnStatement XXreturnStatement;
	public @CHOICE Javascript_SwitchStatement XXswitchStatement;
	public @CHOICE Javascript_ThrowStatement XXthrowStatement;
	public @CHOICE Javascript_TryStatement XXtryStatement;
	public @CHOICE Javascript_WhileStatement XXwhileStatement;

	public @LAST Javascript_ExpressionStmt XXexpressionStmt;
	// public @LAST Javascript_UnparsedStatement XXunparsedStatement;
}
