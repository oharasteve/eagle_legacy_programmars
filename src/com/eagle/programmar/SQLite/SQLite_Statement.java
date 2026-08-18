// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2026

package com.eagle.programmar.SQLite;

import com.eagle.programmar.SQLite.Statements.SQLite_CheckStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_EqpStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_FullSchemaStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_ImportStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_ImposterStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_IndexesStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_IntCkStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_LimitStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_ModeStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_OpenStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_ParamStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_SchemaStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_TablesStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_TestCaseStatement;
import com.eagle.programmar.SQLite.Statements.SQLite_TestCtrlStatement;
import com.eagle.programmar.SQLite.Terminals.SQLite_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class SQLite_Statement extends TokenSequence
{
	public @S(10) SQLite_Stmt statement;
	public @S(20) SQLite_EndOfLine eoln;
	
	public static class SQLite_Stmt extends TokenChooser
	{
		public @CHOICE SQLite_CheckStatement XXcheckStmt;
		public @CHOICE SQLite_EqpStatement XXeqpStmt;
		public @CHOICE SQLite_FullSchemaStatement XXfullSchemaStmt;
		public @CHOICE SQLite_ImportStatement XXimportStmt;
		public @CHOICE SQLite_ImposterStatement XXimposterStmt;
		public @CHOICE SQLite_IndexesStatement XXindexesStmt;
		public @CHOICE SQLite_IntCkStatement XXintckStmt;
		public @CHOICE SQLite_LimitStatement XXlimitStmt;
		public @CHOICE SQLite_ModeStatement XXmodeStmt;
		public @CHOICE SQLite_OpenStatement XXopenStmt;
		public @CHOICE SQLite_ParamStatement XXparamStmt;
		public @CHOICE SQLite_SchemaStatement XXschemaStmt;
		public @CHOICE SQLite_TablesStatement XXtablesStmt;
		public @CHOICE SQLite_TestCaseStatement XXtestCaseStmt;
		public @CHOICE SQLite_TestCtrlStatement XXtestCtrlStmt;
	}
}