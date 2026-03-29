// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Gupta.Expressions
{
	using Gupta_Expression = com.eagle.programmar.Gupta.Gupta_Expression;
	using Gupta_PunctuationChoice = com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Gupta_UnarySign : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice sign = new com.eagle.programmar.Gupta.Terminals.Gupta_PunctuationChoice("-", "+");
		public Gupta_PunctuationChoice sign = new Gupta_PunctuationChoice("-", "+");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Gupta.Gupta_Expression exp;
		public Gupta_Expression exp;
	}

}
