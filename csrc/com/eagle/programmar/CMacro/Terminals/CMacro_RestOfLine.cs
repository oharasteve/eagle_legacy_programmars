// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 29, 2015

namespace com.eagle.programmar.CMacro.Terminals
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using CMacro_Expression = com.eagle.programmar.CMacro.CMacro_Expression;
	using CMacro_Program = com.eagle.programmar.CMacro.CMacro_Program;
	using CMacro_Syntax = com.eagle.programmar.CMacro.CMacro_Syntax;
	using TokenRestOfLine = com.eagle.tokens.TokenRestOfLine;

	public class CMacro_RestOfLine : TokenRestOfLine, EagleRunnable
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			string rec = lines.get(_currentLine).ToString();
			int linesSize = lines.numberLines();
			int lastLine = _currentLine;
			int recLen = rec.Length;
			if (_currentChar >= recLen)
			{
				return false;
			}

			int sc = _currentChar;
			int ec = recLen - 1;
			StringBuilder text = new StringBuilder();
			while (ec >= 0)
			{
				// Look for comments
				if (rec[ec] != '\\')
				{
					int commentPos = rec.IndexOf("/*", sc, StringComparison.Ordinal);
					if (commentPos >= 0)
					{
						ec = commentPos - 1;
					}
					else
					{
						commentPos = rec.IndexOf("//", sc, StringComparison.Ordinal);
						if (commentPos >= 0)
						{
							ec = commentPos - 1;
						}
					}
				}

				// Build the new result, one line at a time
				string piece = rec.Substring(sc, (ec + 1) - sc);
				// Chop off the trailing \, if any
				if (piece.EndsWith("\\", StringComparison.Ordinal))
				{
					piece = piece.Substring(0, piece.Length - 1);
				}
				if (text.Length > 0)
				{
					text.Append('\n');
				}
				text.Append(piece);

				// Not continued (any more)
				if (!rec.EndsWith("\\", StringComparison.Ordinal) || lastLine + 1 >= linesSize)
				{
					break;
				}

				// Must be continued on the next line
				lastLine++;
				rec = lines.get(lastLine).ToString();
				sc = 0;
				ec = rec.Length - 1;
			}

			_txt = text.ToString();
			foundIt(lastLine, ec);
			return true;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// Blank _txt means return ""
			if (_txt.Trim().length() == 0)
			{
				interpreter.pushStr("");
				return;
			}

			// Have to parse it, in order to evaluate it
			CMacro_Program lang = new CMacro_Program();
			CMacro_Syntax syntax = new CMacro_Syntax();
			EagleFileReader lines = new EagleFileReader();
			lines.add(_txt, "none", 0);

			CMacro_Expression expr = new CMacro_Expression();
			expr.setSyntax(syntax);
			if (!interpreter._parser.parseLines(lines, lang, expr))
			{
				throw new Exception("Unable to parse expression " + _txt);
			}

			// Evaluate the newly parsed expression
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.pushEagleValue(val);
		}
	}

}
