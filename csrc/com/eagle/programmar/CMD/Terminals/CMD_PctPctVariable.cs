// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2011

namespace com.eagle.programmar.CMD.Terminals
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public class CMD_PctPctVariable : TerminalIdentifierToken, EagleRunnable
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			int endChar = _currentChar + 1;
			if (endChar >= recLen)
			{
				return false;
			}
			if (rec.charAt(_currentChar) != '%')
			{
				return false;
			}
			char nextCh = rec.charAt(_currentChar + 1);
			if (nextCh == '%')
			{
				// More stuff goes here, starting with ~
				endChar = _currentChar + 2;
				if (endChar >= recLen)
				{
					return false;
				}
				if (!char.IsLetter(rec.charAt(endChar)))
				{
					return false;
				}
			}
			else if (nextCh == '~')
			{
				endChar = _currentChar + 2;
				if (endChar >= recLen)
				{
					return false;
				}
				if (!char.IsDigit(rec.charAt(endChar)))
				{
					return false;
				}
			}
			else if (!char.IsDigit(nextCh))
			{
				return false;
			}

			_id = rec.substring(_currentChar, (endChar + 1) - _currentChar);
			foundIt(_currentLine, endChar);
			return true;
		}

		public override string showString()
		{
			return "Percent Identifier";
		}

		public override string description()
		{
			return "An identifier like %%A or %~2";
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.findSymbol(this.getValue());
			interpreter.pushEagleValue(value);
		}
	}

}
