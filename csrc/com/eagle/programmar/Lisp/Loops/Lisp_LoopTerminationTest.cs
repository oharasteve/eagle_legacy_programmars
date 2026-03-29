// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.Lisp.Loops
{
	using Lisp_Expression = com.eagle.programmar.Lisp.Lisp_Expression;
	using Lisp_KeywordChoice = com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Lisp_LoopTerminationTest : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice when = new com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice("while", "until", "repeat", "always", "never", "thereis");
		public Lisp_KeywordChoice when = new Lisp_KeywordChoice("while", "until", "repeat", "always", "never", "thereis");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Lisp_Expression condition;
		public Lisp_Expression condition;
	}
}
