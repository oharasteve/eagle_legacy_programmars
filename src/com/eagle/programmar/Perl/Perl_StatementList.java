// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Statements.Perl_BreakStatement;
import com.eagle.programmar.Perl.Statements.Perl_ChdirStatement;
import com.eagle.programmar.Perl.Statements.Perl_ChmodStatement;
import com.eagle.programmar.Perl.Statements.Perl_ChompCommand;
import com.eagle.programmar.Perl.Statements.Perl_CloseStatement;
import com.eagle.programmar.Perl.Statements.Perl_ContinueStatement;
import com.eagle.programmar.Perl.Statements.Perl_DieStatement;
import com.eagle.programmar.Perl.Statements.Perl_DoStatement;
import com.eagle.programmar.Perl.Statements.Perl_EchoStatement;
import com.eagle.programmar.Perl.Statements.Perl_EvalStatement;
import com.eagle.programmar.Perl.Statements.Perl_ExitStatement;
import com.eagle.programmar.Perl.Statements.Perl_GlobalStatement;
import com.eagle.programmar.Perl.Statements.Perl_IncludeStatement;
import com.eagle.programmar.Perl.Statements.Perl_MyStatement;
import com.eagle.programmar.Perl.Statements.Perl_NextStatement;
import com.eagle.programmar.Perl.Statements.Perl_OpenStatement;
import com.eagle.programmar.Perl.Statements.Perl_PackageStatement;
import com.eagle.programmar.Perl.Statements.Perl_PrintStatement;
import com.eagle.programmar.Perl.Statements.Perl_RequireStatement;
import com.eagle.programmar.Perl.Statements.Perl_ReturnStatement;
import com.eagle.programmar.Perl.Statements.Perl_ShiftStatement;
import com.eagle.programmar.Perl.Statements.Perl_SleepStatement;
import com.eagle.programmar.Perl.Statements.Perl_ThrowStatement;
import com.eagle.programmar.Perl.Statements.Perl_UnlinkStatement;
import com.eagle.programmar.Perl.Statements.Perl_UseStatement;
import com.eagle.programmar.Perl.Statements.Perl_VarStatement;
import com.eagle.tokens.TokenChooser;

public class Perl_StatementList extends TokenChooser
{
	public @CHOICE Perl_BreakStatement breakStatement;
	public @CHOICE Perl_ChdirStatement chdirStatement;
	public @CHOICE Perl_ChmodStatement chmodStatement;
	public @CHOICE Perl_ChompCommand chompCommand;
	public @CHOICE Perl_CloseStatement closeStatement;
	public @CHOICE Perl_ContinueStatement continueStatement;
	public @CHOICE Perl_DieStatement dieStatement;
	public @CHOICE Perl_DoStatement doStatement;
	public @CHOICE Perl_EchoStatement echoStatement;
	public @CHOICE Perl_EvalStatement evalStatement;
	public @CHOICE Perl_ExitStatement exitStatement;
	public @CHOICE Perl_GlobalStatement globalStatement;
	public @CHOICE Perl_IncludeStatement includeStatement;
	public @CHOICE Perl_MyStatement myStatement;
	public @CHOICE Perl_NextStatement nextStatement;
	public @CHOICE Perl_OpenStatement openStatement;
	public @CHOICE Perl_PackageStatement packageStatement;
	public @CHOICE Perl_PrintStatement printStatement;
	public @CHOICE Perl_RequireStatement requireStatement;
	public @CHOICE Perl_ReturnStatement returnStatement;
	public @CHOICE Perl_ShiftStatement shiftStatement;
	public @CHOICE Perl_SleepStatement sleepStatement;
	public @CHOICE Perl_ThrowStatement throwStatement;
	public @CHOICE Perl_UnlinkStatement unlinkStatement;
	public @CHOICE Perl_UseStatement useStatement;
	public @CHOICE Perl_VarStatement varStatement;
}