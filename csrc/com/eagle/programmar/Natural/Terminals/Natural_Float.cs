// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

namespace com.eagle.programmar.Natural.Terminals
{
	using TerminalNumberToken = com.eagle.tokens.terminals.TerminalNumberToken;

	public class Natural_Float : TerminalNumberToken
	{
		public Natural_Float() : base(null, null, false, false, '?')
		{
		}

	//	@Override
	//	public boolean parse(EagleFileReader lines)
	//	{
	//		if (findStart(lines) == FOUND.EOF) return false;
	//		EagleLineReader rec = lines.get(_currentLine);
	//		int recLen = rec.length();
	//		char ch = rec.charAt(_currentChar);
	//		if (Character.isDigit(ch)
	//				|| (ch == '-' && _currentChar + 1 < recLen && Character.isDigit(rec.charAt(_currentChar + 1)))
	//				|| (ch == '.' && _currentChar + 1 < recLen && Character.isDigit(rec.charAt(_currentChar + 1))))
	//		{
	//			boolean foundDot = (ch == '.');
	//			int endChar = _currentChar;
	//			while (true)
	//			{
	//				endChar++;
	//				if (endChar >= recLen) break;
	//				ch = rec.charAt(endChar);
	//				if (ch == '.' && !foundDot)
	//				{
	//					// Only allow one decimal point
	//					foundDot = true;
	//					continue;
	//				}
	//				if (!Character.isDigit(ch)) break;
	//			}
	//			foundIt(_currentLine, endChar - 1);
	//			_numberAsText = rec.substring(_currentChar, endChar);
	//			return true;
	//		}
	//		return false;
	//	}

		public override string showString()
		{
			return "Float";
		}

		public override string description()
		{
			return "An floating point number, such as 23.5";
		}
	}

}
