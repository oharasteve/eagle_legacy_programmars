// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2026

namespace com.eagle.programmar.FSharp.Expressions
{
	using FSharp_Expression = com.eagle.programmar.FSharp.FSharp_Expression;
	using FSharp_Punctuation = com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class FSharp_Range_Expression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.FSharp_Expression low;
		public FSharp_Expression low;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation dotDot = new com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation("..");
		public FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.FSharp.FSharp_Expression high;
		public FSharp_Expression high;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;
	}

}
