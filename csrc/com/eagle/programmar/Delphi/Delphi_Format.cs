// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2024

namespace com.eagle.programmar.Delphi
{

	using EagleValue = com.eagle.math.EagleValue;

	public class Delphi_Format
	{
		// Handle %d and %s. Super simple ones only for now
		public static string format(string fmt, List<EagleValue> values)
		{
			if (fmt.IndexOf('%') < 0)
			{
				return fmt;
			}

			StringBuilder sb = new StringBuilder();
			int sc = 0;
			int nc = fmt.Length;
			int index = 0;
			int numArgs = values.Count;
			while (sc < nc)
			{
				// Pull in a text string
				int pct = fmt.IndexOf('%', sc);
				if (pct < 0)
				{
					sb.Append(fmt.Substring(sc, nc - sc));
					break; // Done -- no more %
				}
				if (pct > sc)
				{
					sb.Append(fmt.Substring(sc, pct - sc));
				}

				// Insert a variable name (or expression)
				if (index < numArgs)
				{
					sb.Append(values[index]);
				}
				index++;

				// Look for the next piece
				sc = pct + 2;
			}
			return sb.ToString();
		}
	}

}
