// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.COBOL.Expressions
{
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Punctuation = com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class COBOL_ConcatenateExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.COBOL_Expression left = new com.eagle.programmar.COBOL.COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation ampersand = new com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation('&');
		public COBOL_Punctuation ampersand = new COBOL_Punctuation('&');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.COBOL_Expression right = new com.eagle.programmar.COBOL.COBOL_Expression(this, AllowedPrecedence.HIGHER);
		public COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}

}
