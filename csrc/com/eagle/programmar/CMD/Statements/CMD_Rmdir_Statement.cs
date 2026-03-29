// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2011

namespace com.eagle.programmar.CMD.Statements
{
	using CMD_Expression = com.eagle.programmar.CMD.CMD_Expression;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;

	public class CMD_Rmdir_Statement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rmdir.mspx") com.eagle.programmar.CMD.Terminals.CMD_Keyword RMDIR = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("rmdir");
		public @DOC("rmdir.mspx") CMD_Keyword RMDIR = new CMD_Keyword("rmdir");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CMD_Rmdir_Option> opts;
		public @OPT TokenList<CMD_Rmdir_Option> opts;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.CMD_Expression dir;
		public CMD_Expression dir;

		public static class CMD_Rmdir_Option extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Rmdir_Option_Q extends com.eagle.tokens.TokenSequence
			public static class CMD_Rmdir_Option_Q extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
				public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword Q = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("q");
				public CMD_Keyword Q = new CMD_Keyword("q");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Rmdir_Option_S extends com.eagle.tokens.TokenSequence
			public static class CMD_Rmdir_Option_S extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
				public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword S = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("s");
				public CMD_Keyword S = new CMD_Keyword("s");
			}
		}
	}

}
