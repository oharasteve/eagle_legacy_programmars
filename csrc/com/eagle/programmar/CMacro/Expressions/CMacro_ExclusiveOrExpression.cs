// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.CMacro.Expressions
{
	using CMacro_Expression = com.eagle.programmar.CMacro.CMacro_Expression;
	using CMacro_Punctuation = com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class CMacro_ExclusiveOrExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.CMacro_Expression left = new com.eagle.programmar.CMacro.CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation bitwiseXOrOperator = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('^');
		public CMacro_Punctuation bitwiseXOrOperator = new CMacro_Punctuation('^');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMacro.CMacro_Expression right = new com.eagle.programmar.CMacro.CMacro_Expression(this, AllowedPrecedence.HIGHER);
		public CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

}
