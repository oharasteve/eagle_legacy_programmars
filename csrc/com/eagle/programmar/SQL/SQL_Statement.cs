// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

namespace com.eagle.programmar.SQL
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using SQL_BuiltinFunction = com.eagle.programmar.SQL.Functions.SQL_BuiltinFunction;
	using SQL_AlterStatement = com.eagle.programmar.SQL.Statements.SQL_AlterStatement;
	using SQL_AtAtStatement = com.eagle.programmar.SQL.Statements.SQL_AtAtStatement;
	using SQL_BeginStatement = com.eagle.programmar.SQL.Statements.SQL_BeginStatement;
	using SQL_CallStatement = com.eagle.programmar.SQL.Statements.SQL_CallStatement;
	using SQL_CaseStatement = com.eagle.programmar.SQL.Statements.SQL_CaseStatement;
	using SQL_ColumnStatement = com.eagle.programmar.SQL.Statements.SQL_ColumnStatement;
	using SQL_CreateFunctionStatement = com.eagle.programmar.SQL.Statements.SQL_CreateFunctionStatement;
	using SQL_CreateIndexStatement = com.eagle.programmar.SQL.Statements.SQL_CreateIndexStatement;
	using SQL_CreateProcedureStatement = com.eagle.programmar.SQL.Statements.SQL_CreateProcedureStatement;
	using SQL_CreateRoleStatement = com.eagle.programmar.SQL.Statements.SQL_CreateRoleStatement;
	using SQL_CreateSynonymStatement = com.eagle.programmar.SQL.Statements.SQL_CreateSynonymStatement;
	using SQL_CreateTableStatement = com.eagle.programmar.SQL.Statements.SQL_CreateTableStatement;
	using SQL_CreateViewStatement = com.eagle.programmar.SQL.Statements.SQL_CreateViewStatement;
	using SQL_DeclareStatement = com.eagle.programmar.SQL.Statements.SQL_DeclareStatement;
	using SQL_DeleteStatement = com.eagle.programmar.SQL.Statements.SQL_DeleteStatement;
	using SQL_DelimiterStatement = com.eagle.programmar.SQL.Statements.SQL_DelimiterStatement;
	using SQL_DropStatement = com.eagle.programmar.SQL.Statements.SQL_DropStatement;
	using SQL_ForStatement = com.eagle.programmar.SQL.Statements.SQL_ForStatement;
	using SQL_GrantStatement = com.eagle.programmar.SQL.Statements.SQL_GrantStatement;
	using SQL_IfStatement = com.eagle.programmar.SQL.Statements.SQL_IfStatement;
	using SQL_InsertStatement = com.eagle.programmar.SQL.Statements.SQL_InsertStatement;
	using SQL_LeaveStatement = com.eagle.programmar.SQL.Statements.SQL_LeaveStatement;
	using SQL_LoadStatement = com.eagle.programmar.SQL.Statements.SQL_LoadStatement;
	using SQL_PragmaStatement = com.eagle.programmar.SQL.Statements.SQL_PragmaStatement;
	using SQL_ReturnStatement = com.eagle.programmar.SQL.Statements.SQL_ReturnStatement;
	using SQL_SelectStatement = com.eagle.programmar.SQL.Statements.SQL_SelectStatement;
	using SQL_SetStatement = com.eagle.programmar.SQL.Statements.SQL_SetStatement;
	using SQL_SlashStatement = com.eagle.programmar.SQL.Statements.SQL_SlashStatement;
	using SQL_UpdateStatement = com.eagle.programmar.SQL.Statements.SQL_UpdateStatement;
	using SQL_ValuesStatement = com.eagle.programmar.SQL.Statements.SQL_ValuesStatement;
	using SQL_VariableStatement = com.eagle.programmar.SQL.Statements.SQL_VariableStatement;
	using SQL_WhileStatement = com.eagle.programmar.SQL.Statements.SQL_WhileStatement;
	using SQL_WithStatement = com.eagle.programmar.SQL.Statements.SQL_WithStatement;
	using SQL_Comment = com.eagle.programmar.SQL.Terminals.SQL_Comment;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class SQL_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST SQL_Comment XXcomment;
		public SQL_Comment XXcomment;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_AlterStatement XXalterStmt;
		public SQL_AlterStatement XXalterStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_AtAtStatement XXatAtStmt;
		public SQL_AtAtStatement XXatAtStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_BeginStatement XXbeginStmt;
		public SQL_BeginStatement XXbeginStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_CallStatement XXcallStmt;
		public SQL_CallStatement XXcallStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_CaseStatement XXcaseStmt;
		public SQL_CaseStatement XXcaseStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_ColumnStatement XXcolumnStmt;
		public SQL_ColumnStatement XXcolumnStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_CreateFunctionStatement XXcreateFunctionStmt;
		public SQL_CreateFunctionStatement XXcreateFunctionStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_CreateIndexStatement XXcreateIndexStmt;
		public SQL_CreateIndexStatement XXcreateIndexStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_CreateProcedureStatement XXcreateProcedureStmt;
		public SQL_CreateProcedureStatement XXcreateProcedureStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_CreateRoleStatement XXcreateRoleStmt;
		public SQL_CreateRoleStatement XXcreateRoleStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_CreateSynonymStatement XXcreateSynonymStmt;
		public SQL_CreateSynonymStatement XXcreateSynonymStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_CreateTableStatement XXcreateTableStmt;
		public SQL_CreateTableStatement XXcreateTableStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_CreateViewStatement XXcreateViewStmt;
		public SQL_CreateViewStatement XXcreateViewStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_DeclareStatement XXdeclareStmt;
		public SQL_DeclareStatement XXdeclareStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_DeleteStatement XXdeleteStmt;
		public SQL_DeleteStatement XXdeleteStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_DelimiterStatement XXdelimiterStmt;
		public SQL_DelimiterStatement XXdelimiterStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_DropStatement XXdropStmt;
		public SQL_DropStatement XXdropStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_ForStatement XXforStmt;
		public SQL_ForStatement XXforStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_GrantStatement XXgrantStmt;
		public SQL_GrantStatement XXgrantStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_IfStatement XXifStmt;
		public SQL_IfStatement XXifStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_LeaveStatement XXleaveStmt;
		public SQL_LeaveStatement XXleaveStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_LoadStatement XXloadStmt;
		public SQL_LoadStatement XXloadStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_InsertStatement XXinsertStmt;
		public SQL_InsertStatement XXinsertStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_PragmaStatement XXpragmaStmt;
		public SQL_PragmaStatement XXpragmaStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_ReturnStatement XXreturnStmt;
		public SQL_ReturnStatement XXreturnStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_SelectStatement XXselectStmt;
		public SQL_SelectStatement XXselectStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_SetStatement XXsetStmt;
		public SQL_SetStatement XXsetStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_SlashStatement XXslashStmt;
		public SQL_SlashStatement XXslashStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_UpdateStatement XXupdateStmt;
		public SQL_UpdateStatement XXupdateStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_ValuesStatement XXvalueStmt;
		public SQL_ValuesStatement XXvalueStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_VariableStatement XXvariableStmt;
		public SQL_VariableStatement XXvariableStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_WhileStatement XXwhileStmt;
		public SQL_WhileStatement XXwhileStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_WithStatement XXwithStmt;
		public SQL_WithStatement XXwithStmt;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST SQL_BuiltinFunction XXfunctionCall;
		public SQL_BuiltinFunction XXfunctionCall;
		// public @LAST SQL_ExpressionStatement XXexpressionStmt;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_Semicolon extends com.eagle.tokens.TokenSequence implements com.eagle.interpret.EagleRunnable
		public class SQL_Semicolon : TokenSequence, EagleRunnable
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
			public PunctuationSemicolon semicolon;

			public override void interpret(EagleInterpreter interpreter)
			{
				// Nothing to do for a semicolon
			}
		}
	}

}
