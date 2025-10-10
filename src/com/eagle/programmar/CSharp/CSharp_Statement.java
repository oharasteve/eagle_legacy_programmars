// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Directives.CSharp_PragmaDirective;
import com.eagle.programmar.CSharp.Statements.CSharp_BreakStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_CheckedStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ContinueStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_DoWhileStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ExitStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ExpressionStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ForEachStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ForStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_GetProperty;
import com.eagle.programmar.CSharp.Statements.CSharp_GotoStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_IfStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_LockStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ReturnStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_SetProperty;
import com.eagle.programmar.CSharp.Statements.CSharp_StatementBlock;
import com.eagle.programmar.CSharp.Statements.CSharp_SwitchStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_SynchronizedStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ThrowStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_TryStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_UsingStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_WhileStatement;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Statement extends TokenChooser implements AbstractStatement
{
	public @CHOICE @CURIOUS("Extra semicolon") PunctuationSemicolon XXsemicolon;

	public @CHOICE CSharp_Data XXdata;
	public @CHOICE CSharp_Class XXmyclass;
	public @CHOICE CSharp_Enum XXenumeration;
	public @CHOICE @NEWLINE CSharp_PragmaDirective XXpragmaDirective;
	public @CHOICE CSharp_StatementBlock XXstatementBlock;
	
	public @CHOICE CSharp_BreakStatement XXbreakStatement;
	public @CHOICE CSharp_ContinueStatement XXcontinueStatement;
	public @CHOICE CSharp_CheckedStatement XXcheckedStatement;
	public @CHOICE CSharp_DoWhileStatement XXdoStatement;
	public @CHOICE CSharp_ExitStatement XXexitStatement;
	public @CHOICE CSharp_ForStatement XXforStatement;
	public @CHOICE CSharp_ForEachStatement XXforEachStatement;
	public @CHOICE CSharp_GetProperty XXgetProperty;
	public @CHOICE CSharp_GotoStatement XXgotoStatement;
	public @CHOICE CSharp_IfStatement XXifStatement;
	public @CHOICE CSharp_LockStatement XXlockStatement;
	public @CHOICE CSharp_ReturnStatement XXreturnStatement;
	public @CHOICE CSharp_SetProperty XXsetProperty;
	public @CHOICE CSharp_SuperStatement XXsuperStatement;
	public @CHOICE CSharp_SwitchStatement XXswitchStatement;
	public @CHOICE CSharp_SynchronizedStatement XXsynchronizedStatement;
	public @CHOICE CSharp_ThrowStatement XXthrowStatement;
	public @CHOICE CSharp_TryStatement XXtryStatement;
	public @CHOICE CSharp_UsingStatement XXusingStatement;
	public @CHOICE CSharp_WhileStatement XXwhileStatement;

	// Do this one after the others, just because it is so slow
	public @CHOICE CSharp_ExpressionStatement XXassignmentStatement;

	// public @LAST CSharp_UnparsedStatement XXunparsed;
}
