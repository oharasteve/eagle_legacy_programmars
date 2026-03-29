// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.RPG.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class RPG_Literal : TerminalLiteralToken
	{
		private int fixedSc, fixedEc;

		// Used by XML Reader ...
		public RPG_Literal() : this(0, 0)
		{
		}

		public RPG_Literal(int sc, int ec)
		{
			fixedSc = sc - 1;
			fixedEc = ec;
		}

		public override bool parse(EagleFileReader lines)
		{
			EagleLineReader rec = lines.get(_currentLine);
			_endChar = rec.length();
			if (_endChar < fixedSc || fixedSc < 0)
			{
				return false; // Too short
			}
			if (_endChar > fixedEc)
			{
				_endChar = fixedEc;
			}
			_txt = rec.substring(fixedSc, _endChar - fixedSc).Trim();
			foundIt(_currentLine, _endChar - 1);
			return true;
		}

		public override string description()
		{
			return "RPG literal";
		}
	}

}
