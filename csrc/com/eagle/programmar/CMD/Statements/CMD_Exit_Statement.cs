// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2014

namespace com.eagle.programmar.CMD.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using CMD_Expression = com.eagle.programmar.CMD.CMD_Expression;
	using CMD_Label = com.eagle.programmar.CMD.CMD_Label;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;

	public class CMD_Exit_Statement : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("exit.mspx") com.eagle.programmar.CMD.Terminals.CMD_Keyword EXIT = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("exit");
		public @DOC("exit.mspx") CMD_Keyword EXIT = new CMD_Keyword("exit");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CMD_Exit_Option> opts;
		public @OPT TokenList<CMD_Exit_Option> opts;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT CMD_Expression exitValue;
		public @OPT CMD_Expression exitValue;

		public static class CMD_Exit_Option extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Exit_Option_B extends com.eagle.tokens.TokenSequence
			public static class CMD_Exit_Option_B extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
				public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword B = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("b");
				public CMD_Keyword B = new CMD_Keyword("b");
			}
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			int status = interpreter.getIntValue(exitValue);
			CMD_Label func = (CMD_Label) interpreter.getCurrentFunction();
			func._exitStatus = status;
			return Eagle_Statement_Result.RETURN;
		}
	}

}
