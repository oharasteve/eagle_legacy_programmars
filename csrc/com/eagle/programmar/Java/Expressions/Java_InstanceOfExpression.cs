// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Java.Expressions
{
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Type = com.eagle.programmar.Java.Java_Type;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Java_InstanceOfExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Expression expr = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Expression expr = new Java_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Terminals.Java_Keyword instanceOperator = new com.eagle.programmar.Java.Terminals.Java_Keyword("instanceof");
		public Java_Keyword instanceOperator = new Java_Keyword("instanceof");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Java.Java_Type type;
		public Java_Type type;
	}

}
