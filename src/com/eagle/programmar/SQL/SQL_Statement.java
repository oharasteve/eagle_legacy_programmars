// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.SQL.Functions.SQL_BuiltinFunction;
import com.eagle.programmar.SQL.Statements.SQL_AlterStatement;
import com.eagle.programmar.SQL.Statements.SQL_AtAtStatement;
import com.eagle.programmar.SQL.Statements.SQL_BeginStatement;
import com.eagle.programmar.SQL.Statements.SQL_CallStatement;
import com.eagle.programmar.SQL.Statements.SQL_CaseStatement;
import com.eagle.programmar.SQL.Statements.SQL_ColumnStatement;
import com.eagle.programmar.SQL.Statements.SQL_CreateFunctionStatement;
import com.eagle.programmar.SQL.Statements.SQL_CreateIndexStatement;
import com.eagle.programmar.SQL.Statements.SQL_CreateProcedureStatement;
import com.eagle.programmar.SQL.Statements.SQL_CreateRoleStatement;
import com.eagle.programmar.SQL.Statements.SQL_CreateSynonymStatement;
import com.eagle.programmar.SQL.Statements.SQL_CreateTableStatement;
import com.eagle.programmar.SQL.Statements.SQL_CreateViewStatement;
import com.eagle.programmar.SQL.Statements.SQL_DeclareStatement;
import com.eagle.programmar.SQL.Statements.SQL_DeleteStatement;
import com.eagle.programmar.SQL.Statements.SQL_DelimiterStatement;
import com.eagle.programmar.SQL.Statements.SQL_DropStatement;
import com.eagle.programmar.SQL.Statements.SQL_ForStatement;
import com.eagle.programmar.SQL.Statements.SQL_GrantStatement;
import com.eagle.programmar.SQL.Statements.SQL_IfStatement;
import com.eagle.programmar.SQL.Statements.SQL_InsertStatement;
import com.eagle.programmar.SQL.Statements.SQL_LeaveStatement;
import com.eagle.programmar.SQL.Statements.SQL_LoadStatement;
import com.eagle.programmar.SQL.Statements.SQL_PragmaStatement;
import com.eagle.programmar.SQL.Statements.SQL_ReturnStatement;
import com.eagle.programmar.SQL.Statements.SQL_SelectStatement;
import com.eagle.programmar.SQL.Statements.SQL_SetStatement;
import com.eagle.programmar.SQL.Statements.SQL_SlashStatement;
import com.eagle.programmar.SQL.Statements.SQL_UpdateStatement;
import com.eagle.programmar.SQL.Statements.SQL_ValuesStatement;
import com.eagle.programmar.SQL.Statements.SQL_VariableStatement;
import com.eagle.programmar.SQL.Statements.SQL_WhileStatement;
import com.eagle.programmar.SQL.Statements.SQL_WithStatement;
import com.eagle.programmar.SQL.Terminals.SQL_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_Statement extends TokenChooser
{
	public @FIRST SQL_Comment XXcomment;
	
	public @CHOICE SQL_AlterStatement XXalterStmt;
	public @CHOICE SQL_AtAtStatement XXatAtStmt;
	public @CHOICE SQL_BeginStatement XXbeginStmt;
	public @CHOICE SQL_CallStatement XXcallStmt;
	public @CHOICE SQL_CaseStatement XXcaseStmt;
	public @CHOICE SQL_ColumnStatement XXcolumnStmt;
	public @CHOICE SQL_CreateFunctionStatement XXcreateFunctionStmt;
	public @CHOICE SQL_CreateIndexStatement XXcreateIndexStmt;
	public @CHOICE SQL_CreateProcedureStatement XXcreateProcedureStmt;
	public @CHOICE SQL_CreateRoleStatement XXcreateRoleStmt;
	public @CHOICE SQL_CreateSynonymStatement XXcreateSynonymStmt;
	public @CHOICE SQL_CreateTableStatement XXcreateTableStmt;
	public @CHOICE SQL_CreateViewStatement XXcreateViewStmt;
	public @CHOICE SQL_DeclareStatement XXdeclareStmt;
	public @CHOICE SQL_DeleteStatement XXdeleteStmt;
	public @CHOICE SQL_DelimiterStatement XXdelimiterStmt;
	public @CHOICE SQL_DropStatement XXdropStmt;
	public @CHOICE SQL_ForStatement XXforStmt;
	public @CHOICE SQL_GrantStatement XXgrantStmt;
	public @CHOICE SQL_IfStatement XXifStmt;
	public @CHOICE SQL_LeaveStatement XXleaveStmt;
	public @CHOICE SQL_LoadStatement XXloadStmt;
	public @CHOICE SQL_InsertStatement XXinsertStmt;
	public @CHOICE SQL_PragmaStatement XXpragmaStmt;
	public @CHOICE SQL_ReturnStatement XXreturnStmt;
	public @CHOICE SQL_SelectStatement XXselectStmt;
	public @CHOICE SQL_SetStatement XXsetStmt;
	public @CHOICE SQL_SlashStatement XXslashStmt;
	public @CHOICE SQL_UpdateStatement XXupdateStmt;
	public @CHOICE SQL_ValuesStatement XXvalueStmt;
	public @CHOICE SQL_VariableStatement XXvariableStmt;
	public @CHOICE SQL_WhileStatement XXwhileStmt;
	public @CHOICE SQL_WithStatement XXwithStmt;

	public @LAST SQL_BuiltinFunction XXfunctionCall;
	// public @LAST SQL_ExpressionStatement XXexpressionStmt;

	public @CHOICE static class SQL_Semicolon extends TokenSequence implements EagleRunnable
	{
		public @S(10) PunctuationSemicolon semicolon;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			// Nothing to do for a semicolon
		}
	}
}
