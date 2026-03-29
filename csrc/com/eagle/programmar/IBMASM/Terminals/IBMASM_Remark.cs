// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 10, 2011

namespace com.eagle.programmar.IBMASM.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class IBMASM_Remark : TerminalCommentToken
	{
		// Need a default constructor for the parser
		public IBMASM_Remark() : this("")
		{
		}

		public IBMASM_Remark(string remark) : base(remark)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			EagleLineReader rec = lines.get(_currentLine);
			_endChar = rec.length();
			if (_currentChar >= _endChar)
			{
				return false;
			}
			_comment = rec.substring(_currentChar, _endChar - _currentChar).Trim();
			foundIt(_currentLine, _endChar);
			return true;
		}

		public override string showString()
		{
			return "Remark";
		}

		public override string description()
		{
			return "IBMASM remark";
		}
	}

}
