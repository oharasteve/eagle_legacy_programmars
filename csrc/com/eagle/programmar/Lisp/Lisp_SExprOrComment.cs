// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2024

namespace com.eagle.programmar.Lisp
{
	using Lisp_Comment = com.eagle.programmar.Lisp.Terminals.Lisp_Comment;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Lisp_SExprOrComment : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_Expression XXexpr;
		public Lisp_Expression XXexpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_Comment XXcomment;
		public Lisp_Comment XXcomment;
	}

}
