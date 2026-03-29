// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 18, 2022

namespace com.eagle.programmar.CMacro.Terminals
{
	using TerminalCommentRestOfLineToken = com.eagle.tokens.terminals.TerminalCommentRestOfLineToken;

	public class CMacro_CommentRestOfLine : TerminalCommentRestOfLineToken
	{
		// Need a default constructor for the parser
		public CMacro_CommentRestOfLine() : this("")
		{
		}

		public CMacro_CommentRestOfLine(string comment) : base(comment)
		{
		}
	}

}
