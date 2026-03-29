// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 19, 2012

namespace com.eagle.programmar.PLI.Statements
{
	using PLI_Keyword = com.eagle.programmar.PLI.Terminals.PLI_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class PLI_StopStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword STOP = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("STOP");
		public PLI_Keyword STOP = new PLI_Keyword("STOP");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;
	}

}
