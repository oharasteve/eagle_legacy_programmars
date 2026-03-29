// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.C.Expressions
{
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_Punctuation = com.eagle.programmar.C.Terminals.C_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;

	public class C_TrueFalseExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.C_Expression left = new com.eagle.programmar.C.C_Expression(this, AllowedPrecedence.HIGHER);
		public C_Expression left = new C_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_Punctuation questionMark = new com.eagle.programmar.C.Terminals.C_Punctuation('?');
		public C_Punctuation questionMark = new C_Punctuation('?');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.C_Expression middle = new com.eagle.programmar.C.C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Expression middle = new C_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.C.C_Expression rightTF = new com.eagle.programmar.C.C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Expression rightTF = new C_Expression(this, AllowedPrecedence.ATLEAST);
	}

}
