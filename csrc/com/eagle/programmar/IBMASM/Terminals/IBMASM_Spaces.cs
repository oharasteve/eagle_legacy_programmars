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
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class IBMASM_Spaces : TerminalLiteralToken
	{
		private string _spaces;

		// Need a default constructor for the parser
		public IBMASM_Spaces() : this("")
		{
		}

		public IBMASM_Spaces(string spaces)
		{
			_spaces = spaces;
		}

		public override bool parse(EagleFileReader lines)
		{
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			_endChar = _currentChar;
			while (_endChar + 1 < recLen && rec.charAt(_endChar + 1) == ' ')
			{
				_endChar++;
			}
			_spaces = rec.substring(_currentChar, _endChar - _currentChar);
			foundIt(_currentLine, _endChar);
			return true;
		}

		public override string ToString()
		{
			return _spaces;
		}

		public override string Value
		{
			set
			{
				_spaces = value;
				setPresent(true);
			}
		}

		public override string showString()
		{
			return "Spaces";
		}

		public override string description()
		{
			return "IBMASM spaces";
		}
	}

}
