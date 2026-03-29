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

	public class CMD_Copy_Statement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("copy.mspx") com.eagle.programmar.CMD.Terminals.CMD_Keyword COPY = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("copy");
		public @DOC("copy.mspx") CMD_Keyword COPY = new CMD_Keyword("copy");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CMD_Copy_Option> opts;
		public @OPT TokenList<CMD_Copy_Option> opts;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.CMD_Expression copyFrom;
		public CMD_Expression copyFrom;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CMD_Expression copyTo;
		public @OPT CMD_Expression copyTo;

		public static class CMD_Copy_Option extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Copy_Option_Y extends com.eagle.tokens.TokenSequence
			public static class CMD_Copy_Option_Y extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
				public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword Y = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("y");
				public CMD_Keyword Y = new CMD_Keyword("y");
			}
		}
	}

}
