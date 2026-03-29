// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

namespace com.eagle.programmar.IntelASM.Directives
{
	using IntelASM_CommentToEndOfLine = com.eagle.programmar.IntelASM.Terminals.IntelASM_CommentToEndOfLine;
	using IntelASM_Keyword = com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IntelASM_TitleDirective : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword TITLE = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword("TITLE");
		public IntelASM_Keyword TITLE = new IntelASM_Keyword("TITLE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.Terminals.IntelASM_CommentToEndOfLine title;
		public IntelASM_CommentToEndOfLine title;
	}

}
