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
	using Lisp_Keyword = com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Lisp_LoopUnconditionalReturn : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_Keyword RETURN = new com.eagle.programmar.Lisp.Terminals.Lisp_Keyword("return");
		public Lisp_Keyword RETURN = new Lisp_Keyword("return");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Lisp_Expression value;
		public Lisp_Expression value;
	}
}
