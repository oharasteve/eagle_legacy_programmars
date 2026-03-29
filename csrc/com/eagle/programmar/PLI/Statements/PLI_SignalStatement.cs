// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 18, 2011

namespace com.eagle.programmar.PLI.Statements
{
	using PLI_Signal = com.eagle.programmar.PLI.PLI_Signal;
	using PLI_Signals = com.eagle.programmar.PLI.PLI_Signals;
	using PLI_Keyword = com.eagle.programmar.PLI.Terminals.PLI_Keyword;
	using PLI_KeywordChoice = com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class PLI_SignalStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PLI_Signal_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("7.52") com.eagle.programmar.PLI.Terminals.PLI_Keyword SIGNAL = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("SIGNAL");
		public @DOC("7.52") PLI_Keyword SIGNAL = new PLI_Keyword("SIGNAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PLI.PLI_Signal signal;
		public PLI_Signal signal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public static class PLI_Signal_Label extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice which = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice(com.eagle.programmar.PLI.PLI_Signals.signals);
			public PLI_KeywordChoice which = new PLI_KeywordChoice(PLI_Signals.signals);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
		}
	}

}
