// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.AWK.Expressions
{
	using AWK_Expression = com.eagle.programmar.AWK.AWK_Expression;
	using AWK_Punctuation = com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;

	public class AWK_TrueFalseExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.AWK.AWK_Expression left = new com.eagle.programmar.AWK.AWK_Expression(this, AllowedPrecedence.HIGHER);
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.AWK.Terminals.AWK_Punctuation questionMark = new com.eagle.programmar.AWK.Terminals.AWK_Punctuation('?');
		public AWK_Punctuation questionMark = new AWK_Punctuation('?');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.AWK.AWK_Expression middle = new com.eagle.programmar.AWK.AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_Expression middle = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.AWK.AWK_Expression right = new com.eagle.programmar.AWK.AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
	}

}
