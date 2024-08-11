// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash;

import com.eagle.programmar.Bash.Commands.Bash_Assignment;
import com.eagle.programmar.Bash.Commands.Bash_AwkCommand;
import com.eagle.programmar.Bash.Commands.Bash_BashProgram;
import com.eagle.programmar.Bash.Commands.Bash_BreakStatement;
import com.eagle.programmar.Bash.Commands.Bash_CaseCommand;
import com.eagle.programmar.Bash.Commands.Bash_CatCommand;
import com.eagle.programmar.Bash.Commands.Bash_ChmodCommand;
import com.eagle.programmar.Bash.Commands.Bash_CpCommand;
import com.eagle.programmar.Bash.Commands.Bash_DiffCommand;
import com.eagle.programmar.Bash.Commands.Bash_DirnameCommand;
import com.eagle.programmar.Bash.Commands.Bash_EchoCommand;
import com.eagle.programmar.Bash.Commands.Bash_EvaluateCommand;
import com.eagle.programmar.Bash.Commands.Bash_ExportCommand;
import com.eagle.programmar.Bash.Commands.Bash_ForStatement;
import com.eagle.programmar.Bash.Commands.Bash_Function;
import com.eagle.programmar.Bash.Commands.Bash_FunctionCall;
import com.eagle.programmar.Bash.Commands.Bash_GccCommand;
import com.eagle.programmar.Bash.Commands.Bash_GitCommand;
import com.eagle.programmar.Bash.Commands.Bash_GrepCommand;
import com.eagle.programmar.Bash.Commands.Bash_IfStatement;
import com.eagle.programmar.Bash.Commands.Bash_LispCommand;
import com.eagle.programmar.Bash.Commands.Bash_MkTempCommand;
import com.eagle.programmar.Bash.Commands.Bash_MkdirCommand;
import com.eagle.programmar.Bash.Commands.Bash_PerlProgram;
import com.eagle.programmar.Bash.Commands.Bash_PwdCommand;
import com.eagle.programmar.Bash.Commands.Bash_PythonProgram;
import com.eagle.programmar.Bash.Commands.Bash_ReadCommand;
import com.eagle.programmar.Bash.Commands.Bash_ReturnCommand;
import com.eagle.programmar.Bash.Commands.Bash_RmCommand;
import com.eagle.programmar.Bash.Commands.Bash_SedCommand;
import com.eagle.programmar.Bash.Commands.Bash_SetCommand;
import com.eagle.programmar.Bash.Commands.Bash_SortCommand;
import com.eagle.programmar.Bash.Commands.Bash_TeeCommand;
import com.eagle.programmar.Bash.Commands.Bash_UniqCommand;
import com.eagle.programmar.Bash.Commands.Bash_WcCommand;
import com.eagle.programmar.Bash.Commands.Bash_WhileStatement;
import com.eagle.programmar.Bash.Commands.Bash_ZipCommand;
import com.eagle.programmar.Bash.Terminals.Bash_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Bash_Statement extends TokenSequence
{
	public @S(10) Bash_Element element;
	public @S(20) @OPT Bash_EndOfLine eoln;

	public static class Bash_Element extends TokenChooser
	{
		public @CHOICE Bash_AwkCommand XXawkCommand;
		public @CHOICE Bash_BreakStatement XXbreakStatement;
		public @CHOICE Bash_CaseCommand XXcaseCommand;
		public @CHOICE Bash_CatCommand XXcatCommand;
		public @CHOICE Bash_ChmodCommand XXchmodCommand;
		public @CHOICE Bash_Comment XXcomment;
		public @CHOICE Bash_CpCommand XXcpCommand;
		public @CHOICE Bash_DiffCommand XXdiffCommand;
		public @CHOICE Bash_DirnameCommand XXdirnameCommand;
		public @CHOICE Bash_EchoCommand XXechoCommand;
		public @CHOICE Bash_EvaluateCommand XXevaluateCommand;
		public @CHOICE Bash_ExportCommand XXexportCommand;
		public @CHOICE Bash_ForStatement XXforStatement;
		public @CHOICE Bash_GccCommand XXgccCommand;
		public @CHOICE Bash_GitCommand XXgitCommand;
		public @CHOICE Bash_GrepCommand XXgrepCommand;
		public @CHOICE Bash_IfStatement XXifStatement;
		public @CHOICE Bash_LispCommand XXlispCommand;
		public @CHOICE Bash_MkdirCommand XXmkdirCommand;
		public @CHOICE Bash_MkTempCommand XXmktempCommand;
		public @CHOICE Bash_PwdCommand XXpwdCommand;
		public @CHOICE Bash_ReadCommand XXreadCommand;
		public @CHOICE Bash_ReturnCommand XXreturnCommand;
		public @CHOICE Bash_RmCommand XXrmCommand;
		public @CHOICE Bash_SedCommand XXsedCommand;
		public @CHOICE Bash_SetCommand XXsetCommand;
		public @CHOICE Bash_SortCommand XXsortCommand;
		public @CHOICE Bash_TeeCommand XXteeCommand;
		public @CHOICE Bash_UniqCommand XXuniqCommand;
		public @CHOICE Bash_WcCommand XXwcCommand;
		public @CHOICE Bash_WhileStatement XXwhileStatement;
		public @CHOICE Bash_ZipCommand XXzipCommand;

		public @CHOICE Bash_BashProgram XXbashProgram;
		public @CHOICE Bash_PerlProgram XXperlProgram;
		public @CHOICE Bash_PythonProgram XXpythonProgram;

		public @LAST Bash_Assignment XXassignment;
		public @LAST Bash_FunctionCall XXfunctionCall;
		public @LAST Bash_Condition XXcondition;
		public @LAST Bash_Function XXfunction;
	}
}
