// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.Statements.C_AutoLock;
import com.eagle.programmar.C.Statements.C_BreakStatement;
import com.eagle.programmar.C.Statements.C_ContinueStatement;
import com.eagle.programmar.C.Statements.C_DoStatement;
import com.eagle.programmar.C.Statements.C_Embed_Assembler;
import com.eagle.programmar.C.Statements.C_ExpressionStatement;
import com.eagle.programmar.C.Statements.C_ForStatement;
import com.eagle.programmar.C.Statements.C_GotoStatement;
import com.eagle.programmar.C.Statements.C_IfStatement;
import com.eagle.programmar.C.Statements.C_ReturnStatement;
import com.eagle.programmar.C.Statements.C_StatementBlock;
import com.eagle.programmar.C.Statements.C_SwitchStatement;
import com.eagle.programmar.C.Statements.C_WhileStatement;
import com.eagle.programmar.C.Types.C_TypeStruct;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_Statement extends TokenChooser implements AbstractStatement
{
	public @CHOICE C_Data XXjdata;
	public @CHOICE C_Label XXlabel;
	public @CHOICE PunctuationSemicolon XXsemicolon; // Empty for loop body is ok

	public @CHOICE @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment XXmacro;

	public @CHOICE C_StatementBlock XXstatementBlock;

	public @CHOICE C_Embed_Assembler XXassembler;

	public @CHOICE C_AutoLock XXautoLock;
	public @CHOICE C_BreakStatement XXbreakStatement;
	public @CHOICE C_ContinueStatement XXcontinueStatement;
	public @CHOICE C_DoStatement XXdoStatement;
	public @CHOICE C_ForStatement XXforStatement;
	public @CHOICE C_GotoStatement XXgotoStatement;
	public @CHOICE C_IfStatement XXifStatement;
	public @CHOICE C_ReturnStatement XXreturnStatement;
	public @CHOICE C_SwitchStatement XXswitchStatement;
	public @CHOICE C_WhileStatement XXwhileStatement;

	public @CHOICE C_Declaration XXdeclaration; // Like [[fallthrough]]
	public @LAST C_TypeStruct XXstructDefinition; // Like struct bob_t;

	// Do this one last, just because it is so slow
	public @CHOICE C_ExpressionStatement XXassignmentStatement;

	// public @LAST C_UnparsedStatement XXunparsedStatement;
}
