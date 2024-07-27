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
import com.eagle.programmar.CMD.Statements.CMD_FindStr_Statement;
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
import com.eagle.programmar.CMD.Statements.CMD_Xcopy_Statement;
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
		public @CHOICE CMD_Redirect_Input XXredirectInput;
		public @CHOICE CMD_Redirect_Output XXredirectOutput;
		public @CHOICE CMD_Redirect_Append XXredirectAppend;
		public @CHOICE CMD_Redirect_Error_One XXredirectErrorOne;
		public @CHOICE CMD_Redirect_Error_File XXredirectErrorFile;
	}

	// Some need a wrapper because they have CMD_Statement's inside of themselves

	public static class CMD_Statement extends TokenChooser
	{
		public @CHOICE CMD_Comment XXcomment;

		public @CHOICE static class CMD_BlockStatement extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) CMD_EndOfLine eoln;
			public @S(30) TokenList<CMD_CommandOrLabel> commands;
			public @S(40) PunctuationRightParen rightParen;
			public @S(50) @OPT CMD_IfElse ifElse;

			public static class CMD_CommandOrLabel extends TokenChooser
			{
				public @CHOICE CMD_Command XXcommand;
				public @CHOICE CMD_Label XXlabel;
			}

			public static class CMD_IfElse extends TokenSequence
			{
				public @S(10) CMD_Keyword ELSE = new CMD_Keyword("else");
				public @S(20) @OPT CMD_Punctuation at = new CMD_Punctuation('@');
				public @S(30) CMD_Statement stmt;
			}
		}

		public @LAST static class CMD_GenericStatement extends TokenSequence
		{
			public @S(10) CMD_Argument programName;
			public @S(20) @OPT TokenList<CMD_GenericArgument> args;

			public static class CMD_GenericArgument extends TokenChooser
			{
				public @CHOICE CMD_Argument XXarg;
				public @CHOICE CMD_PunctuationChoice XXminus = new CMD_PunctuationChoice("-", "/");
			}
		}

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

	public static class CMD_Redirect_Error_One extends TokenSequence
	{
		public @S(10) CMD_Number two;
		public @S(20) CMD_Punctuation greater = new CMD_Punctuation(">");
		public @S(30) CMD_Punctuation ampersand = new CMD_Punctuation("&");
		public @S(40) CMD_Number one;
	}

	public static class CMD_Redirect_Error_File extends TokenSequence
	{
		public @S(10) CMD_Number two;
		public @S(20) CMD_Punctuation greater = new CMD_Punctuation(">");
		public @S(30) CMD_Argument file;
	}

	public static class CMD_More_Statements extends TokenSequence
	{
		public @S(10) CMD_Statement_Separator separator;
		public @S(20) CMD_Statement command;
		public @S(30) @OPT TokenList<CMD_Redirect> redirects;

		public static class CMD_Statement_Separator extends TokenChooser
		{
			public @CHOICE CMD_PunctuationChoice XXseparator = new CMD_PunctuationChoice("||", "|", "&&");
		}
	}
}
