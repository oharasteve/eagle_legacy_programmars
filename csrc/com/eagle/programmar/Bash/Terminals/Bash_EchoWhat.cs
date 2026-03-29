// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 18, 2024

namespace com.eagle.programmar.Bash.Terminals
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using Bash_Expression = com.eagle.programmar.Bash.Bash_Expression;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class Bash_EchoWhat : TerminalLiteralToken, EagleRunnable
	{
		public Bash_EchoWhat() : base("'\"", true, '\\', false, false)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			int endChar = _currentChar;
			if (endChar >= recLen)
			{
				return false;
			}

			bool inQuotes1 = false;
			bool inQuotes2 = false;
			while (endChar < recLen)
			{
				char ch = rec.charAt(endChar);
				if (!inQuotes2 && ch == '\'')
				{
					inQuotes1 = !inQuotes1;
				}
				if (!inQuotes1 && ch == '"')
				{
					inQuotes2 = !inQuotes2;
				}

				if (!inQuotes1 && !inQuotes2)
				{
					if (ch == '<' || ch == '>' || ch == '|' || ch == '&' || ch == ';')
					{
						endChar--;
						break;
					}
				}
				endChar++;
			}

			foundIt(_currentLine, endChar);
			_txt += rec.substring(_currentChar, endChar - _currentChar);
			return true;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			string text = removeQuotes();
			if (_txt.StartsWith("'"))
			{
				interpreter.pushStr(text);
				return;
			}

			if (text.IndexOf("$((", StringComparison.Ordinal) < 0)
			{
				interpreter.pushStr(text);
				return;
			}

			StringBuilder sb = new StringBuilder();
			int sc = 0;
			int nc = text.Length;
			while (sc < nc)
			{
				// Pull in a text string
				int first = text.IndexOf("$((", sc, StringComparison.Ordinal);
				if (first < 0)
				{
					sb.Append(text.Substring(sc, nc - sc));
					break; // Done -- no more $((
				}
				if (first > sc)
				{
					sb.Append(text.Substring(sc, first - sc));
				}

				// Extract a variable name (or expression) and value
				int second = text.IndexOf("))", first + 3, StringComparison.Ordinal);
				while (second + 2 < nc && text[second + 2] == ')')
				{
					second++;
				} // In case there is something like $(((1+2)))
				if (second < 0)
				{
					throw new Exception("Missing )) in " + text);
				}
				string var = text.Substring(first + 3, second - (first + 3));
				Bash_Expression expr = new Bash_Expression();
				if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
				{
					throw new Exception("Unable to parse expression " + var);
				}
				string val = interpreter.getStrValue(expr);
				sb.Append(val);

				// Look for the next piece
				sc = second + 2;
			}
			interpreter.pushStr(sb.ToString());
		}
	}

}
