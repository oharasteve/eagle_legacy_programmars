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
	public @CHOICE Perl_BreakStatement XXbreakStatement;
	public @CHOICE Perl_ChdirStatement XXchdirStatement;
	public @CHOICE Perl_ChmodStatement XXchmodStatement;
	public @CHOICE Perl_ChompCommand XXchompCommand;
	public @CHOICE Perl_CloseStatement XXcloseStatement;
	public @CHOICE Perl_ContinueStatement XXcontinueStatement;
	public @CHOICE Perl_DieStatement XXdieStatement;
	public @CHOICE Perl_DoStatement XXdoStatement;
	public @CHOICE Perl_EchoStatement XXechoStatement;
	public @CHOICE Perl_EvalStatement XXevalStatement;
	public @CHOICE Perl_ExitStatement XXexitStatement;
	public @CHOICE Perl_GlobalStatement XXglobalStatement;
	public @CHOICE Perl_IncludeStatement XXincludeStatement;
	public @CHOICE Perl_MyStatement XXmyStatement;
	public @CHOICE Perl_NextStatement XXnextStatement;
	public @CHOICE Perl_OpenStatement XXopenStatement;
	public @CHOICE Perl_PackageStatement XXpackageStatement;
	public @CHOICE Perl_PrintStatement XXprintStatement;
	public @CHOICE Perl_RequireStatement XXrequireStatement;
	public @CHOICE Perl_ReturnStatement XXreturnStatement;
	public @CHOICE Perl_ShiftStatement XXshiftStatement;
	public @CHOICE Perl_SleepStatement XXsleepStatement;
	public @CHOICE Perl_ThrowStatement XXthrowStatement;
	public @CHOICE Perl_UnlinkStatement XXunlinkStatement;
	public @CHOICE Perl_UseStatement XXuseStatement;
	public @CHOICE Perl_VarStatement XXvarStatement;
}