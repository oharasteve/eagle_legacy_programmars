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
	using CMD_Variable_Definition = com.eagle.programmar.CMD.Symbols.CMD_Variable_Definition;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;

	public class CMD_NMake_Statement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Terminals.CMD_Keyword NMAKE = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("nmake");
		public CMD_Keyword NMAKE = new CMD_Keyword("nmake");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<CMD_NMake_Parameter> params;
		public TokenList<CMD_NMake_Parameter> @params;

		public class CMD_NMake_Parameter : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Expression XXtarget;
			public CMD_Expression XXtarget;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_NMake_Option_I extends com.eagle.tokens.TokenSequence
			public class CMD_NMake_Option_I : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
				public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword I = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("i");
				public CMD_Keyword I = new CMD_Keyword("i");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_NMake_Option_K extends com.eagle.tokens.TokenSequence
			public class CMD_NMake_Option_K : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
				public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword K = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("k");
				public CMD_Keyword K = new CMD_Keyword("k");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_NMake_Option_E extends com.eagle.tokens.TokenSequence
			public class CMD_NMake_Option_E : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
				public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword E = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("e");
				public CMD_Keyword E = new CMD_Keyword("e");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_NMake_Option_F extends com.eagle.tokens.TokenSequence
			public class CMD_NMake_Option_F : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
				public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword F = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("f");
				public CMD_Keyword F = new CMD_Keyword("f");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.CMD_Expression makefile;
				public CMD_Expression makefile;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CMD_NMake_Assignment extends com.eagle.tokens.TokenSequence
			public class CMD_NMake_Assignment : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.Symbols.CMD_Variable_Definition var;
				public CMD_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.CMD_Expression value;
				public CMD_Expression value;
			}
		}
	}

}
