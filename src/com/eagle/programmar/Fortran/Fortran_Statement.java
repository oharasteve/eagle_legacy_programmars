// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 13, 2022

package com.eagle.programmar.Fortran;

import com.eagle.programmar.Fortran.Statements.Fortran_Assignment;
import com.eagle.programmar.Fortran.Statements.Fortran_CallStatement;
import com.eagle.programmar.Fortran.Statements.Fortran_Common;
import com.eagle.programmar.Fortran.Statements.Fortran_Data;
import com.eagle.programmar.Fortran.Statements.Fortran_DoStatement;
import com.eagle.programmar.Fortran.Statements.Fortran_ExitStatement;
import com.eagle.programmar.Fortran.Statements.Fortran_Function;
import com.eagle.programmar.Fortran.Statements.Fortran_IfStatement;
import com.eagle.programmar.Fortran.Statements.Fortran_Implicit;
import com.eagle.programmar.Fortran.Statements.Fortran_PrintStatement;
import com.eagle.programmar.Fortran.Statements.Fortran_ProgramBlock;
import com.eagle.programmar.Fortran.Statements.Fortran_Subroutine;
import com.eagle.programmar.Fortran.Statements.Fortran_WriteStatement;
import com.eagle.programmar.Fortran.Terminals.Fortran_Comment;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Fortran_Statement extends TokenChooser
{
	public @CHOICE Fortran_Assignment assignment;
	public @CHOICE Fortran_CallStatement callStatement;
	public @CHOICE Fortran_Common common;
	public @CHOICE Fortran_Data data;
	public @CHOICE Fortran_DoStatement doStatement;
	public @CHOICE Fortran_ExitStatement exitStatement;
	public @CHOICE Fortran_Function function;
	public @CHOICE Fortran_IfStatement ifStatement;
	public @CHOICE Fortran_Implicit implicit;
	public @CHOICE Fortran_PrintStatement printStatement;
	public @CHOICE Fortran_ProgramBlock programBlock;
	public @CHOICE Fortran_Subroutine subroutine;
	public @CHOICE Fortran_WriteStatement writeStatement;
	
	public @CHOICE static class Fortran_CommentEOLN extends TokenSequence
	{
		public @S(10) Fortran_Comment comment;
		public @S(20) Fortran_EOLN eoln;
	}
}
