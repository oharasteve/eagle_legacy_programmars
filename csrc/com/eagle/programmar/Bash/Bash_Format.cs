// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

namespace com.eagle.programmar.Bash
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleValue = com.eagle.math.EagleValue;

	public class Bash_Format
	{
		public static string format(EagleInterpreter interpreter, string fmt)
		{
			if (fmt.StartsWith("'", StringComparison.Ordinal))
			{
				return fmt;
			}

			string txt = fmt;
			if (txt.StartsWith("\"", StringComparison.Ordinal))
			{
				// Toss leading and trailing quotes, if present
				txt = txt.Substring(1, (txt.Length - 1) - 1);
			}

			// Change \" to "
			txt = txt.replaceAll("\\\\", "\\");

			if (txt.IndexOf('$') < 0)
			{
				return txt;
			}

			StringBuilder sb = new StringBuilder();
			int sc = 0;
			int nc = txt.Length;
			while (sc < nc)
			{
				// Pull in a text string
				int first = txt.IndexOf('$', sc);
				if (first < 0)
				{
					sb.Append(txt.Substring(sc, nc - sc));
					break; // Done -- no more $
				}
				if (first > sc)
				{
					sb.Append(txt.Substring(sc, first - sc));
				}
				if (first + 1 < nc && txt[first + 1] == '{')
				{
					// Extract an expression
					int second = txt.IndexOf("}", first + 2, StringComparison.Ordinal);
					if (second < 0)
					{
						throw new Exception("Missing } in " + txt);
					}
					string var = txt.Substring(first + 2, second - (first + 2));
					Bash_Expression expr = new Bash_Expression();
					if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
					{
						throw new Exception("Unable to parse expression " + var);
					}
					string val = interpreter.getStrValue(expr);
					sb.Append(val);

					// Look for the next piece
					sc = second + 1;
				}
				else
				{
					// Just a variable, like $str
					int second = first + 1;
					while (second < nc)
					{
						char ch = txt[second];
						if (!char.IsLetterOrDigit(ch))
						{
							break;
						}
						second++;
					}
					if (first + 1 == second)
					{
						// Just a lonely $
						sb.Append('$');
						sc = first + 1;
					}
					else
					{
						string varName = txt.Substring(first + 1, second - (first + 1));
						EagleValue value = interpreter.findSymbol(varName);
						if (value == null)
						{
							throw new Exception("Unable to find variable " + varName);
						}
						sb.Append(value.forceStringValue());
						sc = second;
					}
				}
			}
			return sb.ToString();
		}
	}

}
