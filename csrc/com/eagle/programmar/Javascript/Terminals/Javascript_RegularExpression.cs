// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 10, 2014

namespace com.eagle.programmar.Javascript.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalRegularExpression = com.eagle.tokens.terminals.TerminalRegularExpression;

	public class Javascript_RegularExpression : TerminalRegularExpression
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			if (!genericRegEx(lines, "/", '\\'))
			{
				return false;
			}

			// Don't pick up a comment instead of a regular expression!
			char ch = _regex.charAt(1);
			if (ch == '/' || ch == '*')
			{
				return false; // Both "//" and "/*" are comments
			}

			// Check for modifiers
			EagleLineReader rec = lines.get(_endLine);
			int recLen = rec.length();
			while (_endChar + 1 < recLen)
			{
				ch = rec.charAt(_endChar + 1);
				// ignore case, global, multiline modifiers
				if (ch != 'i' && ch != 'g' && ch != 'm')
				{
					break;
				}
				_regex += ch;
				_endChar++;
			}

			return true;
		}
	}

}
