// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 13, 2022

package com.eagle.programmar.Fortran;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
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
import com.eagle.programmar.Fortran.Statements.Fortran_WhileStatement;
import com.eagle.programmar.Fortran.Statements.Fortran_WriteStatement;
import com.eagle.programmar.Fortran.Terminals.Fortran_Comment;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Fortran_Statement extends TokenChooser
{
	public @CHOICE Fortran_Assignment XXassignment;
	public @CHOICE Fortran_CallStatement XXcallStatement;
	public @CHOICE Fortran_Common XXcommon;
	public @CHOICE Fortran_Data XXdata;
	public @CHOICE Fortran_DoStatement XXdoStatement;
	public @CHOICE Fortran_ExitStatement XXexitStatement;
	public @CHOICE Fortran_Function XXfunction;
	public @CHOICE Fortran_IfStatement XXifStatement;
	public @CHOICE Fortran_Implicit XXimplicit;
	public @CHOICE Fortran_PrintStatement XXprintStatement;
	public @CHOICE Fortran_ProgramBlock XXprogramBlock;
	public @CHOICE Fortran_Subroutine XXsubroutine;
	public @CHOICE Fortran_WriteStatement XXwriteStatement;
	public @CHOICE Fortran_WhileStatement XXwhileStatement;

	public @CHOICE static class Fortran_CommentEOLN extends TokenSequence implements EagleRunnable
	{
		public @S(10) Fortran_Comment comment;
		public @S(20) Fortran_EOLN eoln;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			// Nothing to do here
		}
	}
}
