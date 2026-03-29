// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.Javascript.Terminals
{
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class Javascript_Literal : TerminalLiteralToken
	{
		public Javascript_Literal() : base("`\"'", true, '\\', false, true)
		{
		}

	//	@Override
	//	public boolean parse(EagleFileReader lines)
	//	{
	//		if (findStart(lines) == FOUND.EOF) return false;
	//		EagleLineReader rec = lines.get(_currentLine);
	//		char ch = rec.charAt(_currentChar);
	//		if (ch == '`')
	//		{
	//			// backticks can span multiple lines, and can inject values with $(x) inside
	//			return genericLiteral(lines, "`", true, '\\', false, true);
	//		}
	//
	//		return genericLiteral(lines, "\"'", true, '\\', false, false);
	//	}
	}

}
