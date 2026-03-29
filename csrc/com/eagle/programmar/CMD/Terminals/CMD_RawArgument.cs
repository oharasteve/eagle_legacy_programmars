// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

namespace com.eagle.programmar.CMD.Terminals
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using CMD_Expression = com.eagle.programmar.CMD.CMD_Expression;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class CMD_RawArgument : TerminalLiteralToken, EagleRunnable
	{
		public CMD_RawArgument() : base("'\"", false, '?', false, false)
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
			if (_currentChar >= recLen)
			{
				return false;
			}
			char ch = rec.charAt(_currentChar);
			if (":@<>|&),".IndexOf(ch) >= 0)
			{
				return false;
			}

			int offset = 0;
			char quote;
			if (ch == '!' || ch == '$')
			{
				return false;
			}
			else if (ch == '"' || ch == '\'')
			{
				quote = ch;

				_endChar = _currentChar;
				while (true)
				{
					_endChar++;
					if (_endChar >= recLen)
					{
						break;
					}
					ch = rec.charAt(_endChar);
					if (ch == quote)
					{
						break;
					}
				}
				_txt = rec.substring(_currentChar + 1, _endChar - (_currentChar + 1));
			}
			else if (ch == '/')
			{
				// Allow /%%x
				if (_currentChar + 3 >= recLen)
				{
					return false;
				}
				if (rec.charAt(_currentChar + 1) != '%')
				{
					return false;
				}
				if (rec.charAt(_currentChar + 2) != '%')
				{
					return false;
				}
				ch = rec.charAt(_currentChar + 3);
				if (!char.IsLetter(ch))
				{
					return false;
				}
				_endChar = _currentChar + 3;
			}
			else
			{
				// Plain argument, no quotes
				_endChar = _currentChar;
				while (true)
				{
					_endChar++;
					if (_endChar >= recLen)
					{
						break;
					}
					ch = rec.charAt(_endChar);
					if (" <>|&(),".IndexOf(ch) >= 0)
					{
						break;
					}

					// Don't allow == in the middle of an argument
					if (ch == '=')
					{
						if (_endChar + 1 < recLen)
						{
							if (rec.charAt(_endChar + 1) == '=')
							{
								break;
							}
						}
					}
				}
				_txt = rec.substring(_currentChar, _endChar - _currentChar);

				// Make sure a generic argument is not a program name like SET or REM or
				// whatever
				string word = _txt.ToUpper();
				int dot = word.IndexOf('.');
				if (dot > 0)
				{
					word = word.Substring(0, dot);
				}
				EagleSyntax syntax = getSyntax();
				if (syntax.isReservedWord(word))
				{
					return false;
				}
				offset = 1;
			}

			foundIt(_currentLine, _endChar - offset);
			return true;
		}

		public override string description()
		{
			return "argument";
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			string txt = _txt.replaceAll("\"", "");
			if (txt.IndexOf('%') < 0)
			{
				interpreter.pushStr(txt);
				return;
			}

			StringBuilder sb = new StringBuilder();
			int sc = 0;
			int nc = txt.Length;
			while (sc < nc)
			{
				// Pull in a text string
				int first = txt.IndexOf('%', sc);
				if (first < 0)
				{
					sb.Append(txt.Substring(sc, nc - sc));
					break; // Done -- no more %
				}
				if (first > sc)
				{
					sb.Append(txt.Substring(sc, first - sc));
				}

				// Extract a variable name (or expression) and value
				string var;
				int ec;
				if (first + 2 < nc && txt[first + 1] == '~' && char.IsDigit(txt[first + 2]))
				{
					var = txt.Substring(first, 3); // Treat %~2 special
					ec = first + 2;
				}
				else if (first + 2 < nc && txt[first + 1] == '%' && char.IsLetter(txt[first + 2]))
				{
					var = txt.Substring(first, 3); // Treat %%A special
					ec = first + 2;
				}
				else
				{
					ec = txt.IndexOf('%', first + 1);
					if (ec < 0)
					{
						throw new Exception("Missing second % in " + txt);
					}
					var = txt.Substring(first + 1, ec - (first + 1));
				}

				CMD_Expression expr = new CMD_Expression();
				if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
				{
					throw new Exception("Unable to parse expression " + var);
				}
				string val = interpreter.getStrValue(expr);
				sb.Append(val);

				// Look for the next piece
				sc = ec + 1;
			}
			interpreter.pushStr(sb.ToString());
		}
	}

}
