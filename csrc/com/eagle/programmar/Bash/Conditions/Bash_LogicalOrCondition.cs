// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

namespace com.eagle.programmar.Bash.Conditions
{
	using Bash_Condition = com.eagle.programmar.Bash.Bash_Condition;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using PunctuationHyphen = com.eagle.tokens.punctuation.PunctuationHyphen;

	public class Bash_LogicalOrCondition : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Bash_Condition left = new com.eagle.programmar.Bash.Bash_Condition(this, AllowedPrecedence.ATLEAST);
		public Bash_Condition left = new Bash_Condition(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationHyphen dash;
		public PunctuationHyphen dash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Bash.Terminals.Bash_Keyword OR = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("or");
		public Bash_Keyword OR = new Bash_Keyword("or");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Bash.Bash_Condition right = new com.eagle.programmar.Bash.Bash_Condition(this, AllowedPrecedence.HIGHER);
		public Bash_Condition right = new Bash_Condition(this, AllowedPrecedence.HIGHER);
	}
}
