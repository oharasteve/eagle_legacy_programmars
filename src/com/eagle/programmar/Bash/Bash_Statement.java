// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash;

import com.eagle.programmar.Bash.Commands.Bash_Assignment;
import com.eagle.programmar.Bash.Commands.Bash_AwkCommand;
import com.eagle.programmar.Bash.Commands.Bash_BashProgram;
import com.eagle.programmar.Bash.Commands.Bash_BreakStatement;
import com.eagle.programmar.Bash.Commands.Bash_CatCommand;
import com.eagle.programmar.Bash.Commands.Bash_ChmodCommand;
import com.eagle.programmar.Bash.Commands.Bash_DiffCommand;
import com.eagle.programmar.Bash.Commands.Bash_EchoCommand;
import com.eagle.programmar.Bash.Commands.Bash_EvaluateCommand;
import com.eagle.programmar.Bash.Commands.Bash_ExportCommand;
import com.eagle.programmar.Bash.Commands.Bash_ForStatement;
import com.eagle.programmar.Bash.Commands.Bash_Function;
import com.eagle.programmar.Bash.Commands.Bash_FunctionCall;
import com.eagle.programmar.Bash.Commands.Bash_GccCommand;
import com.eagle.programmar.Bash.Commands.Bash_GrepCommand;
import com.eagle.programmar.Bash.Commands.Bash_IfStatement;
import com.eagle.programmar.Bash.Commands.Bash_LispCommand;
import com.eagle.programmar.Bash.Commands.Bash_MkTempCommand;
import com.eagle.programmar.Bash.Commands.Bash_PerlProgram;
import com.eagle.programmar.Bash.Commands.Bash_PwdCommand;
import com.eagle.programmar.Bash.Commands.Bash_PythonProgram;
import com.eagle.programmar.Bash.Commands.Bash_ReadCommand;
import com.eagle.programmar.Bash.Commands.Bash_RmCommand;
import com.eagle.programmar.Bash.Commands.Bash_SedCommand;
import com.eagle.programmar.Bash.Commands.Bash_SetCommand;
import com.eagle.programmar.Bash.Commands.Bash_TeeCommand;
import com.eagle.programmar.Bash.Commands.Bash_WhileStatement;
import com.eagle.programmar.Bash.Terminals.Bash_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Bash_Statement extends TokenSequence
{
	public @S(10) Bash_Element element;
	public @S(20) @OPT Bash_EndOfLine eoln;

	public static class Bash_Element extends TokenChooser
	{
		public @CHOICE Bash_AwkCommand awkCommand;
		public @CHOICE Bash_BreakStatement breakStatement;
		public @CHOICE Bash_CatCommand catCommand;
		public @CHOICE Bash_ChmodCommand chmodCommand;
		public @CHOICE Bash_Comment comment;
		public @CHOICE Bash_DiffCommand diffCommand;
		public @CHOICE Bash_EchoCommand echoCommand;
		public @CHOICE Bash_EvaluateCommand evaluateCommand;
		public @CHOICE Bash_ExportCommand exportCommand;
		public @CHOICE Bash_ForStatement forStatement;
		public @CHOICE Bash_Function function;
		public @CHOICE Bash_GccCommand gccCommand;
		public @CHOICE Bash_GrepCommand grepCommand;
		public @CHOICE Bash_IfStatement ifStatement;
		public @CHOICE Bash_LispCommand lispCommand;
		public @CHOICE Bash_MkTempCommand mktempCommand;
		public @CHOICE Bash_PwdCommand pwdCommand;
		public @CHOICE Bash_ReadCommand readCommand;
		public @CHOICE Bash_RmCommand rmCommand;
		public @CHOICE Bash_SedCommand sedCommand;
		public @CHOICE Bash_SetCommand setCommand;
		public @CHOICE Bash_TeeCommand teeCommand;
		public @CHOICE Bash_WhileStatement whileStatement;

		public @CHOICE Bash_BashProgram bashProgram;
		public @CHOICE Bash_PerlProgram perlProgram;
		public @CHOICE Bash_PythonProgram pythonProgram;

		public @LAST Bash_Assignment assignment;
		public @LAST Bash_FunctionCall functionCall;
		public @LAST Bash_Condition condition;
	}
}
