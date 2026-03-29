// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 27, 2024

namespace com.eagle.programmar.Lisp
{
	using Lisp_KeywordChoice = com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
	using Lisp_Punctuation = com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Lisp_Type : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation QUOTE = new com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation("'");
		public Lisp_Punctuation QUOTE = new Lisp_Punctuation("'");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice STRING = new com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice("STRING");
		public Lisp_KeywordChoice STRING = new Lisp_KeywordChoice("STRING");
	}

}
