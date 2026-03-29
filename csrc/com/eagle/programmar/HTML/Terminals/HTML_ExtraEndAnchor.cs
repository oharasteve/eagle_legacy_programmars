// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 11, 2022

namespace com.eagle.programmar.HTML.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using HTML_Anchor = com.eagle.programmar.HTML.HTML_Anchor;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class HTML_ExtraEndAnchor : TerminalLiteralToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (_currentChar + 3 >= recLen)
			{
				return false;
			}
			char ch3 = rec.charAt(_currentChar + 2);

			if (rec.charAt(_currentChar) == '<' && rec.charAt(_currentChar + 1) == '/' && (ch3 == 'a' || ch3 == 'A') && rec.charAt(_currentChar + 3) == '>')
			{
				// Have to fail if we are already inside an <a>
				AbstractToken parent = getParent();
				while (parent != null)
				{
					if (parent is HTML_Anchor)
					{
						return false;
					}
					parent = parent.getParent();
				}

				_endLine = _currentLine;
				_txt = rec.substring(_currentChar, 4);
				foundIt(_endLine, _currentChar + 4);
				return true;
			}
			return false;
		}

		public override string description()
		{
			return "html extra end anchor";
		}
	}

}
