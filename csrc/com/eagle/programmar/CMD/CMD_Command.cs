// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

namespace com.eagle.programmar.CMD
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using CMD_EndOfLine = com.eagle.programmar.CMD.Terminals.CMD_EndOfLine;
	using CMD_Number = com.eagle.programmar.CMD.Terminals.CMD_Number;
	using CMD_Punctuation = com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
	using CMD_PunctuationChoice = com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CMD_Command : TokenSequence, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT CMD_EndOfLine eoln1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CMD_Punctuation at = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('@');
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CMD_Statement command;
		public CMD_Statement command;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<CMD_Redirect> redirects;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<CMD_More_Statements> moreStatements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.CMD.Terminals.CMD_EndOfLine eoln2;
		public CMD_EndOfLine eoln2;

		public class CMD_Redirect : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Redirect_Input XXredirectInput;
			public CMD_Redirect_Input XXredirectInput;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Redirect_Output XXredirectOutput;
			public CMD_Redirect_Output XXredirectOutput;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Redirect_Append XXredirectAppend;
			public CMD_Redirect_Append XXredirectAppend;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Redirect_Error_One XXredirectErrorOne;
			public CMD_Redirect_Error_One XXredirectErrorOne;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Redirect_Error_File XXredirectErrorFile;
			public CMD_Redirect_Error_File XXredirectErrorFile;
		}

		// Some need a wrapper because they have CMD_Statement's inside of themselves

		public class CMD_Redirect_Input : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Punctuation less = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('<');
			public CMD_Punctuation less = new CMD_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CMD_Expression inFile;
			public CMD_Expression inFile;
		}

		public class CMD_Redirect_Output : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Punctuation greater = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('>');
			public CMD_Punctuation greater = new CMD_Punctuation('>');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CMD_Expression outFile;
			public CMD_Expression outFile;
		}

		public class CMD_Redirect_Append : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Punctuation greaterGreater = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation(">>");
			public CMD_Punctuation greaterGreater = new CMD_Punctuation(">>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CMD_Expression appFile;
			public CMD_Expression appFile;
		}

		public class CMD_Redirect_Error_One : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Number two;
			public CMD_Number two;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Punctuation greater = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation(">");
			public CMD_Punctuation greater = new CMD_Punctuation(">");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.Terminals.CMD_Punctuation ampersand = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation("&");
			public CMD_Punctuation ampersand = new CMD_Punctuation("&");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CMD.Terminals.CMD_Number one;
			public CMD_Number one;
		}

		public class CMD_Redirect_Error_File : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Number two;
			public CMD_Number two;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Punctuation greater = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation(">");
			public CMD_Punctuation greater = new CMD_Punctuation(">");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CMD_Expression file;
			public CMD_Expression file;
		}

		public class CMD_More_Statements : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) CMD_Statement_Separator separator;
			public CMD_Statement_Separator separator;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CMD_Statement command;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<CMD_Redirect> redirects;
			public  OPT;

			public class CMD_Statement_Separator : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_PunctuationChoice XXseparator = new com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice(";", "||", "|", "&&");
				public CMD_PunctuationChoice XXseparator = new CMD_PunctuationChoice(";", "||", "|", "&&");
			}
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			return interpreter.tryToInterpret(command);
		}
	}

}
