// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 9, 2011

namespace com.eagle.programmar.CMD.Statements
{
	using CMD_Expression = com.eagle.programmar.CMD.CMD_Expression;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using CMD_Punctuation = com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationHyphen = com.eagle.tokens.punctuation.PunctuationHyphen;

	public class CMD_Grep_Statement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Keyword GREP = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("grep");
		public CMD_Keyword GREP = new CMD_Keyword("grep");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<CMD_Grep_Parameter> params;
		public TokenList<CMD_Grep_Parameter> @params;

		public class CMD_Grep_Parameter : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Expression XXsrcFile;
			public CMD_Expression XXsrcFile;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Grep_Option_e extends com.eagle.tokens.TokenSequence
			public class CMD_Grep_Option_e : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Punctuation hyphen = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('-');
				public CMD_Punctuation hyphen = new CMD_Punctuation('-');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword E = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("e");
				public CMD_Keyword E = new CMD_Keyword("e");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.CMD_Expression pattern;
				public CMD_Expression pattern;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Grep_Option_H extends com.eagle.tokens.TokenSequence
			public class CMD_Grep_Option_H : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Punctuation hyphen = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('-');
				public CMD_Punctuation hyphen = new CMD_Punctuation('-');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword H = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("H");
				public CMD_Keyword H = new CMD_Keyword("H");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Grep_Option_i extends com.eagle.tokens.TokenSequence
			public class CMD_Grep_Option_i : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Punctuation hyphen = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('-');
				public CMD_Punctuation hyphen = new CMD_Punctuation('-');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword I = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("i");
				public CMD_Keyword I = new CMD_Keyword("i");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Grep_Option_l extends com.eagle.tokens.TokenSequence
			public class CMD_Grep_Option_l : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Punctuation hyphen = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('-');
				public CMD_Punctuation hyphen = new CMD_Punctuation('-');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword L = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("l");
				public CMD_Keyword L = new CMD_Keyword("l");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Grep_Option_n extends com.eagle.tokens.TokenSequence
			public class CMD_Grep_Option_n : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Punctuation hyphen = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('-');
				public CMD_Punctuation hyphen = new CMD_Punctuation('-');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword N = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("n");
				public CMD_Keyword N = new CMD_Keyword("n");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Grep_Option_v extends com.eagle.tokens.TokenSequence
			public class CMD_Grep_Option_v : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Punctuation hyphen = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation('-');
				public CMD_Punctuation hyphen = new CMD_Punctuation('-');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword V = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("v");
				public CMD_Keyword V = new CMD_Keyword("v");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_Grep_Option_w extends com.eagle.tokens.TokenSequence
			public class CMD_Grep_Option_w : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationHyphen minus;
				public PunctuationHyphen minus;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword W = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("w");
				public CMD_Keyword W = new CMD_Keyword("w");
			}
		}
	}

}
