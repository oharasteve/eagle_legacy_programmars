// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Julia.Expressions
{
	using Julia_Expression = com.eagle.programmar.Julia.Julia_Expression;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;

	public class Julia_RangeExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Julia.Julia_Expression first = new com.eagle.programmar.Julia.Julia_Expression(this, AllowedPrecedence.ATLEAST);
		public Julia_Expression first = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Julia.Julia_Expression lastOrIncrement = new com.eagle.programmar.Julia.Julia_Expression(this, AllowedPrecedence.HIGHER);
		public Julia_Expression lastOrIncrement = new Julia_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Julia_Range_HasIncrement hasIncr;
		public  OPT;

		public class Julia_Range_HasIncrement : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Julia.Julia_Expression last;
			public Julia_Expression last;
		}
	}

}
