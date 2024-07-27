// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Statements.PLI_AllocateStatement;
import com.eagle.programmar.PLI.Statements.PLI_AnswerStatement;
import com.eagle.programmar.PLI.Statements.PLI_AssignmentStatement;
import com.eagle.programmar.PLI.Statements.PLI_BeginStatement;
import com.eagle.programmar.PLI.Statements.PLI_CallStatement;
import com.eagle.programmar.PLI.Statements.PLI_DoStatement;
import com.eagle.programmar.PLI.Statements.PLI_FormatStatement;
import com.eagle.programmar.PLI.Statements.PLI_FreeStatement;
import com.eagle.programmar.PLI.Statements.PLI_GetStatement;
import com.eagle.programmar.PLI.Statements.PLI_GoStatement;
import com.eagle.programmar.PLI.Statements.PLI_IfStatement;
import com.eagle.programmar.PLI.Statements.PLI_IterateStatement;
import com.eagle.programmar.PLI.Statements.PLI_LeaveStatement;
import com.eagle.programmar.PLI.Statements.PLI_NoteStatement;
import com.eagle.programmar.PLI.Statements.PLI_OnStatement;
import com.eagle.programmar.PLI.Statements.PLI_OpenStatement;
import com.eagle.programmar.PLI.Statements.PLI_PercentStatement;
import com.eagle.programmar.PLI.Statements.PLI_PutStatement;
import com.eagle.programmar.PLI.Statements.PLI_ReturnStatement;
import com.eagle.programmar.PLI.Statements.PLI_RevertStatement;
import com.eagle.programmar.PLI.Statements.PLI_SelectStatement;
import com.eagle.programmar.PLI.Statements.PLI_SignalStatement;
import com.eagle.programmar.PLI.Statements.PLI_StopStatement;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_Statement extends TokenChooser
{
	public @CHOICE PunctuationSemicolon XXsemicolon; // Empty statement

	public @LAST PLI_AssignmentStatement XXassignmentStatement;

	public @CHOICE PLI_AllocateStatement XXallocateStatement;
	public @CHOICE PLI_AnswerStatement XXanswerStatement;
	public @CHOICE PLI_BeginStatement XXbeginStatement;
	public @CHOICE PLI_CallStatement XXcallStatement;
	public @CHOICE PLI_DoStatement XXdoStatement;
	public @CHOICE PLI_FormatStatement XXformatStatement;
	public @CHOICE PLI_FreeStatement XXfreeStatement;
	public @CHOICE PLI_GetStatement XXgetStatement;
	public @CHOICE PLI_GoStatement XXgoStatement;
	public @CHOICE PLI_IfStatement XXifStatement;
	public @CHOICE PLI_IterateStatement XXiterateStatement;
	public @CHOICE PLI_LeaveStatement XXleaveStatement;
	public @CHOICE PLI_NoteStatement XXnoteStatement;
	public @CHOICE PLI_OnStatement XXonStatement;
	public @CHOICE PLI_OpenStatement XXopenStatement;
	public @CHOICE PLI_PercentStatement XXpercentStmt;
	public @CHOICE PLI_PutStatement XXputStatement;
	public @CHOICE PLI_ReturnStatement XXreturnStatement;
	public @CHOICE PLI_RevertStatement XXrevertStatement;
	public @CHOICE PLI_SignalStatement XXsignalStatement;
	public @CHOICE PLI_SelectStatement XXselectStatement;
	public @CHOICE PLI_StopStatement XXstopStatement;

	public @CHOICE PLI_Procedure XXinnerProcedure;
}
