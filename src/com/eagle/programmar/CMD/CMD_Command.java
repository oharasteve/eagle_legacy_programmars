// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.CMD;

import com.eagle.programmar.CMD.CMD_Program.CMD_Label;
import com.eagle.programmar.CMD.Statements.CMD_Awk_Statement;
import com.eagle.programmar.CMD.Statements.CMD_CD_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Call_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Copy_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Del_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Dir_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Echo_Statement;
import com.eagle.programmar.CMD.Statements.CMD_Exit_Statement;
import com.eagle.programmar.CMD.Statements.CMD_For_Statement;
import com.eagle.programmar.CMD.Statements.CMD_GCC_Statement;
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
import com.eagle.programmar.CMD.Terminals.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Comment;
import com.eagle.programmar.CMD.Terminals.CMD_EndOfLine;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMD_Command extends TokenSequence
{
	public @S(10) @OPT CMD_EndOfLine eoln1;
	public @S(20) @OPT CMD_Punctuation at = new CMD_Punctuation('@');
	public @S(30) CMD_Statement command;
	public @S(40) @OPT TokenList<CMD_Redirect> redirects;
	public @S(50) @OPT TokenList<CMD_More_Statements> moreStatements;
	public @S(60) CMD_EndOfLine eoln2;

	public static class CMD_Redirect extends TokenChooser
	{
		public @CHOICE CMD_Redirect_Input redirectInput;
		public @CHOICE CMD_Redirect_Output redirectOutput;
		public @CHOICE CMD_Redirect_Append redirectAppend;
		public @CHOICE CMD_Redirect_Error redirectError;
	}

	// Some need a wrapper because they have CMD_Statement's inside of themselves

	public static class CMD_Statement extends TokenChooser
	{
		public @CHOICE CMD_Comment comment;

		public @CHOICE static class CMD_BlockStatement extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) CMD_EndOfLine eoln;
			public @S(30) TokenList<CMD_CommandOrLabel> commands;
			public @S(40) PunctuationRightParen rightParen;
			public @S(50) @OPT CMD_IfElse ifElse;
			
			public static class CMD_CommandOrLabel extends TokenChooser
			{
				public @CHOICE CMD_Command command;
				public @CHOICE CMD_Label label;
			}
			
			public static class CMD_IfElse extends TokenSequence
			{
				public @S(10) CMD_Keyword ELSE = new CMD_Keyword("else");
				public @S(20) @OPT CMD_Punctuation at = new CMD_Punctuation('@');
				public @S(30) CMD_Statement stmt;
			}
		}
		
		public @CHOICE static class CMD_GenericStatement extends TokenSequence
		{
			public @S(10) CMD_Argument programName;
			public @S(20) @OPT TokenList<CMD_GenericArgument> args;
			
			public static class CMD_GenericArgument extends TokenChooser
			{
				public @CHOICE CMD_Argument arg;
				public @CHOICE CMD_PunctuationChoice minus = new CMD_PunctuationChoice("-", "/");
			}
		}
		
		public @CHOICE CMD_Awk_Statement awkCommand;
		public @CHOICE CMD_Call_Statement callCommand;
		public @CHOICE CMD_CD_Statement cdCommand;
		public @CHOICE CMD_Copy_Statement copyCommand;
		public @CHOICE CMD_Del_Statement delCommand;
		public @CHOICE CMD_Dir_Statement dirCommand;
		public @CHOICE CMD_Echo_Statement echoCommand;
		public @CHOICE CMD_Exit_Statement exitCommand;
		public @CHOICE CMD_For_Statement forCommand;
		public @CHOICE CMD_GCC_Statement gccCommand;
		public @CHOICE CMD_Goto_Statement gotoCommand;
		public @CHOICE CMD_Grep_Statement grepCommand;
		public @CHOICE CMD_If_Statement ifCommand;
		public @CHOICE CMD_Mkdir_Statement mkdirCommand;
		public @CHOICE CMD_NMake_Statement nmakeCommand;
		public @CHOICE CMD_Perl_Statement perlCommand;
		public @CHOICE CMD_Popd_Statement popdCommand;
		public @CHOICE CMD_Pushd_Statement pushdCommand;
		public @CHOICE CMD_Rem_Statement remCommand;
		public @CHOICE CMD_Rmdir_Statement rmdirCommand;
		public @CHOICE CMD_Set_Statement setCommand;
		public @CHOICE CMD_SetLocal_Statement setLocalCommand;
		public @CHOICE CMD_Shift_Statement shiftCommand;
	}
	
	public static class CMD_Redirect_Input extends TokenSequence
	{
		public @S(10) CMD_Punctuation less = new CMD_Punctuation('<');
		public @S(20) CMD_Argument inFile;
	}

	public static class CMD_Redirect_Output extends TokenSequence
	{
		public @S(10) CMD_Punctuation greater = new CMD_Punctuation('>');
		public @S(20) CMD_Argument outFile;
	}

	public static class CMD_Redirect_Append extends TokenSequence
	{
		public @S(10) CMD_Punctuation greaterGreater = new CMD_Punctuation(">>");
		public @S(20) CMD_Argument appFile;
	}

	public static class CMD_Redirect_Error extends TokenSequence
	{
		public @S(10) CMD_Number two;
		public @S(20) CMD_Punctuation greaterAnd = new CMD_Punctuation(">&");
		public @S(30) CMD_Number one;
	}
	
	public static class CMD_More_Statements extends TokenSequence
	{
		public @S(10) CMD_Statement_Separator separator;
		public @S(20) CMD_Statement command;
		public @S(30) @OPT TokenList<CMD_Redirect> redirects;
		
		public static class CMD_Statement_Separator extends TokenChooser
		{
			public @CHOICE CMD_PunctuationChoice separator = new CMD_PunctuationChoice("||", "|", "&&");
		}
	}
}
