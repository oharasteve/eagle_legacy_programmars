// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 19, 2012

namespace com.eagle.programmar.PLI.Statements
{
	using PLI_Label = com.eagle.programmar.PLI.PLI_Label;
	using PLI_PutFormat = com.eagle.programmar.PLI.Statements.PLI_PutStatement.PLI_PutFormat;
	using PLI_Keyword = com.eagle.programmar.PLI.Terminals.PLI_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class PLI_FormatStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PLI_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Terminals.PLI_Keyword FORMAT = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("FORMAT");
		public PLI_Keyword FORMAT = new PLI_Keyword("FORMAT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PLI.Statements.PLI_PutStatement.PLI_PutFormat format;
		public PLI_PutFormat format;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;
	}

}
