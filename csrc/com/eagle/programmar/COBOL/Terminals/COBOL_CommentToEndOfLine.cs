// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

namespace com.eagle.programmar.COBOL.Terminals
{
	using TerminalCommentRestOfLineToken = com.eagle.tokens.terminals.TerminalCommentRestOfLineToken;

	public class COBOL_CommentToEndOfLine : TerminalCommentRestOfLineToken
	{
		public COBOL_CommentToEndOfLine() : this("")
		{
		}

		public COBOL_CommentToEndOfLine(string comment) : base(comment)
		{
		}
	}

}
