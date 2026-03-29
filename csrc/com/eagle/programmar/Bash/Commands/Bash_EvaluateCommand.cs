// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 23, 2022

namespace com.eagle.programmar.Bash.Commands
{
	using Bash_Expression = com.eagle.programmar.Bash.Bash_Expression;
	using Bash_Punctuation = com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Bash_EvaluateCommand : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Punctuation leftParenParen = new com.eagle.programmar.Bash.Terminals.Bash_Punctuation("((");
		public Bash_Punctuation leftParenParen = new Bash_Punctuation("((");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Bash_Expression expr;
		public Bash_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Bash.Terminals.Bash_Punctuation rightParenParen = new com.eagle.programmar.Bash.Terminals.Bash_Punctuation("))");
		public Bash_Punctuation rightParenParen = new Bash_Punctuation("))");
	}

}
