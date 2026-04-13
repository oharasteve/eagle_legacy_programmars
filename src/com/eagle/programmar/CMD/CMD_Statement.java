// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2024

package com.eagle.programmar.CMD;

import com.eagle.programmar.CMD.Statements.CMD_Awk_Statement;
import com.eagle.programmar.CMD.Statements.CMD_BlockStatement;
import com.eagle.programmar.CMD.Statements.CMD_CD_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Call_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Copy_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Del_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Dir_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Echo_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Exit_Statement;
import com.eagle.programmar.CMD.Statements.CMD_FindStr_Statement;
import com.eagle.programmar.CMD.Statements.CMD_For_Statement;
import com.eagle.programmar.CMD.Statements.CMD_GCC_Statement;
import com.eagle.programmar.CMD.Statements.CMD_GenericStatement;
import com.eagle.programmar.CMD.Statements.CMD_Goto_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Grep_Statement;
import com.eagle.programmar.CMD.Statements.CMD_If_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Mkdir_Statement;
import com.eagle.programmar.CMD.Statements.CMD_NMake_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Perl_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Popd_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Pushd_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Rem_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Rmdir_Statement;
import com.eagle.programmar.CMD.Statements.CMD_SetLocal_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Set_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Shift_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Xcopy_Statement;
import com.eagle.programmar.CMD.Terminals.CMD_Comment;
import com.eagle.tokens.TokenChooser;

public class CMD_Statement extends TokenChooser
{
	public @CHOICE CMD_Comment XXcomment;

	public @CHOICE CMD_BlockStatement XXblockStatement;

	public @CHOICE CMD_Awk_Statement XXawkCommand;
	public @CHOICE CMD_Call_Statement XXcallCommand;
	public @CHOICE CMD_CD_Statement XXcdCommand;
	public @CHOICE CMD_Copy_Statement XXcopyCommand;
	public @CHOICE CMD_Del_Statement XXdelCommand;
	public @CHOICE CMD_Dir_Statement XXdirCommand;
	public @CHOICE CMD_Echo_Statement XXechoCommand;
	public @CHOICE CMD_Exit_Statement XXexitCommand;
	public @CHOICE CMD_FindStr_Statement XXfindstrCommand;
	public @CHOICE CMD_For_Statement XXforCommand;
	public @CHOICE CMD_GCC_Statement XXgccCommand;
	public @CHOICE CMD_Goto_Statement XXgotoCommand;
	public @CHOICE CMD_Grep_Statement XXgrepCommand;
	public @CHOICE CMD_If_Statement XXifCommand;
	public @CHOICE CMD_Mkdir_Statement XXmkdirCommand;
	public @CHOICE CMD_NMake_Statement XXnmakeCommand;
	public @CHOICE CMD_Perl_Statement XXperlCommand;
	public @CHOICE CMD_Popd_Statement XXpopdCommand;
	public @CHOICE CMD_Pushd_Statement XXpushdCommand;
	public @CHOICE CMD_Rem_Statement XXremCommand;
	public @CHOICE CMD_Rmdir_Statement XXrmdirCommand;
	public @CHOICE CMD_Set_Statement XXsetCommand;
	public @CHOICE CMD_SetLocal_Statement XXsetLocalCommand;
	public @CHOICE CMD_Shift_Statement XXshiftCommand;
	public @CHOICE CMD_Xcopy_Statement XXxcopyCommand;

	public @LAST CMD_Expression XXexpressionStatement;
	public @LAST CMD_GenericStatement XXgenericStatement;
}
