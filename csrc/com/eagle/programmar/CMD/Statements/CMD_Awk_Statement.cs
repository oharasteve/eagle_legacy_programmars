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
	using PunctuationHyphen = com.eagle.tokens.punctuation.PunctuationHyphen;

	public class CMD_Awk_Statement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Keyword AWK = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("awk");
		public CMD_Keyword AWK = new CMD_Keyword("awk");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CMD_Awk_Option> opts;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.CMD.CMD_Expression> arg;
		public  OPT;

		public class CMD_Awk_Option : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Awk_Option_F extends com.eagle.tokens.TokenSequence
			public class CMD_Awk_Option_F : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationHyphen minus;
				public PunctuationHyphen minus;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword F = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("f");
				public CMD_Keyword F = new CMD_Keyword("f");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.CMD_Expression arg;
				public CMD_Expression arg;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Awk_Option_V extends com.eagle.tokens.TokenSequence
			public class CMD_Awk_Option_V : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationHyphen minus;
				public PunctuationHyphen minus;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword V = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("v");
				public CMD_Keyword V = new CMD_Keyword("v");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.CMD_Expression variableDefinitions;
				public CMD_Expression variableDefinitions;
			}
		}
	}

}
