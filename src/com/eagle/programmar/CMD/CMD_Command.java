// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.CMD;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.CMD.Terminals.CMD_EndOfLine;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMD_Command extends TokenSequence implements EagleRunnableWithResult
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

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return interpreter.tryToInterpret(command);
	}
}
