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
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class AWK_DollarParensExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.AWK.Terminals.AWK_Punctuation dollar = new com.eagle.programmar.AWK.Terminals.AWK_Punctuation('$');
		public AWK_Punctuation dollar = new AWK_Punctuation('$');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.AWK.AWK_Expression expression;
		public AWK_Expression expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
	}

}
