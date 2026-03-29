// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Powershell.Expressions
{
	using Powershell_Expression = com.eagle.programmar.Powershell.Powershell_Expression;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class Powershell_SubfieldExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Powershell_Expression left = new com.eagle.programmar.Powershell.Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Powershell_Expression right = new com.eagle.programmar.Powershell.Powershell_Expression(this, AllowedPrecedence.HIGHER);
		public  NOSPACE;
	}

}
