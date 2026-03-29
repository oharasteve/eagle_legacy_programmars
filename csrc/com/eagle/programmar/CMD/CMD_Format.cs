// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2024

namespace com.eagle.programmar.CMD
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleValue = com.eagle.math.EagleValue;

	public class CMD_Format
	{
		public static string format(EagleInterpreter interpreter, string fmt)
		{
			string txt = fmt.replaceAll("\"", "");

			if (txt.IndexOf('%') >= 0)
			{
				StringBuilder sb = new StringBuilder();
				int sc = 0;
				int nc = txt.Length;
				while (sc < nc)
				{
					// Pull in a text string
					int firstPct = txt.IndexOf('%', sc);
					if (firstPct < 0)
					{
						sb.Append(txt.Substring(sc, nc - sc));
						break; // Done -- no more %
					}

					// Copy over plain text between %vars%
					if (firstPct > sc)
					{
						sb.Append(txt.Substring(sc, firstPct - sc));
					}

					// Check for %%I
					if (firstPct + 2 < nc)
					{
						if (txt[firstPct + 1] == '%')
						{
							if (char.IsLetter(txt[firstPct + 2]))
							{
								string var = txt.Substring(firstPct, 3);
								EagleValue val = interpreter.findSymbol(var);
								sb.Append(val.forceStringValue());
								sc = firstPct + 3;
								continue;
							}
						}
					}

					// Check for %~2
					if (firstPct + 2 < nc)
					{
						if (txt[firstPct + 1] == '~')
						{
							if (char.IsDigit(txt[firstPct + 2]))
							{
								string var = txt.Substring(firstPct, 3);
								EagleValue val = interpreter.findSymbol(var);
								sb.Append(val.forceStringValue());
								sc = firstPct + 3;
								continue;
							}
						}
					}

					// Extract a variable name (or expression) and value, like %abc%
					int secondPct = txt.IndexOf('%', firstPct + 1);
					if (secondPct < 0)
					{
						throw new Exception("Missing % in " + txt);
					}
					string var = txt.Substring(firstPct + 1, secondPct - (firstPct + 1));
					EagleValue val = interpreter.findSymbol(var);
					if (val == null)
					{
						throw new Exception("Unable to find variable '" + var + "'");
					}
					sb.Append(val.forceStringValue());

					// Look for the next piece
					sc = secondPct + 1;
				}
				txt = sb.ToString();
			}

			if (txt.IndexOf('!') >= 0)
			{
				StringBuilder sb = new StringBuilder();
				int sc = 0;
				int nc = txt.Length;
				while (sc < nc)
				{
					// Pull in a text string
					int firstBang = txt.IndexOf('!', sc);
					if (firstBang < 0)
					{
						sb.Append(txt.Substring(sc, nc - sc));
						break; // Done -- no more !
					}

					// Copy over plain text between !vars!
					if (firstBang > sc)
					{
						sb.Append(txt.Substring(sc, firstBang - sc));
					}

					// Extract a variable name (or expression) and value, like !abc!
					int secondBang = txt.IndexOf('!', firstBang + 1);
					if (secondBang < 0)
					{
						throw new Exception("Missing ! in " + txt);
					}
					string name = txt.Substring(firstBang + 1, secondBang - (firstBang + 1));

					int colon = name.IndexOf(":~", StringComparison.Ordinal);
					int comma = name.IndexOf(',');
					int subSC = 0;
					int subNC = 0;
					if (colon > 0 && comma > colon)
					{
						subSC = int.Parse(name.Substring(colon + 2, comma - (colon + 2)));
						subNC = int.Parse(name.Substring(comma + 1));
						name = name.Substring(0, colon);
					}

					EagleValue val = interpreter.findSymbol(name);
					string piece = val.forceStringValue();

					if (colon > 0)
					{
						// CMD allows access beyond the end.
						// Java does not. We have to validate sc and ec
						int pieceNC = piece.Length;
						if (subSC >= pieceNC)
						{
							piece = "";
						}
						else
						{
							int subEC = subSC + subNC;
							if (subEC > pieceNC)
							{
								subEC = pieceNC;
							}
							piece = piece.Substring(subSC, subEC - subSC);
						}
					}

					sb.Append(piece);

					// Look for the next piece
					sc = secondBang + 1;
				}
				txt = sb.ToString();
			}

			return txt;
		}
	}

}
