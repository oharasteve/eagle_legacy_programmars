// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

namespace com.eagle.programmar.Gupta.Statements
{
	using Gupta_Expression = com.eagle.programmar.Gupta.Gupta_Expression;
	using Gupta_Keyword = com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Gupta_Return_Statement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Return = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Return");
		public Gupta_Keyword Return = new Gupta_Keyword("Return");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Gupta.Gupta_Expression expression;
		public Gupta_Expression expression;
	}

}
