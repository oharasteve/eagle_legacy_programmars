// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 16, 2024

namespace com.eagle.programmar.Perl.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class Perl_MultilineString : TerminalLiteralToken
	{
		public Perl_MultilineString() : base("\"'`", true, '\\', false, true)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			// Check for <<<STOPPER lines STOPPER
			EagleLineReader rec = lines.get(_currentLine);
			if (multilineStopper(lines, rec, "<<<"))
			{
				return true;
			}
			if (multilineStopper(lines, rec, "<<"))
			{
				return true;
			}

			return base.parse(lines);
		}
	}

}
