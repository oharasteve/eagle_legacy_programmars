// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.PLI.Expressions
{
	using PLI_RepeatCount = com.eagle.programmar.PLI.PLI_RepeatCount;
	using PLI_HexNumber = com.eagle.programmar.PLI.Terminals.PLI_HexNumber;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenList = com.eagle.tokens.TokenList;

	public class PLI_RepeatedHexLiteral : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<com.eagle.programmar.PLI.PLI_RepeatCount> repeat;
		public TokenList<PLI_RepeatCount> repeat;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Terminals.PLI_HexNumber literal;
		public PLI_HexNumber literal;
	}

}
