// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Expression = com.eagle.programmar.Natural.Natural_Expression;
	using Natural_Variable = com.eagle.programmar.Natural.Natural_Variable;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class Natural_ComputeStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/compute.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword COMPUTE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("COMPUTE");
		public @DOC("sm/compute.htm") Natural_Keyword COMPUTE = new Natural_Keyword("COMPUTE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Natural_Variable var;
		public Natural_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Natural.Natural_Expression expr;
		public Natural_Expression expr;
	}

}
