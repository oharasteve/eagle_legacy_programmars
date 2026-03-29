// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Delphi.Expressions
{
	using Delphi_Expression = com.eagle.programmar.Delphi.Delphi_Expression;
	using Delphi_Punctuation = com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Delphi_DotDot_Expression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Delphi_Expression left = new com.eagle.programmar.Delphi.Delphi_Expression(this, AllowedPrecedence.HIGHER);
		public Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation dotDot = new com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation("..");
		public Delphi_Punctuation dotDot = new Delphi_Punctuation("..");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Delphi.Delphi_Expression right = new com.eagle.programmar.Delphi.Delphi_Expression(this, AllowedPrecedence.HIGHER);
		public Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
	}

}
