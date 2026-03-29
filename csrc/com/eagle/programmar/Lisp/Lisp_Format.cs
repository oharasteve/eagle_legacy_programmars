// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

namespace com.eagle.programmar.Lisp
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using TokenList = com.eagle.tokens.TokenList;

	public class Lisp_Format
	{
		// Handle ~d and ~A. Super simple ones only for now
		public static string format(EagleInterpreter interpreter, TokenList<Lisp_Expression> exprs)
		{
			string fmt = interpreter.getStrValue(exprs._elements.get(0));
			fmt = fmt.replaceAll("~%", ""); // Remove newlines from format
			if (fmt.IndexOf('~') < 0)
			{
				return fmt;
			}

			StringBuilder sb = new StringBuilder();
			int sc = 0;
			int nc = fmt.Length;
			int index = 0;
			int numArgs = exprs._elements.size();
			while (sc < nc)
			{
				// Pull in a text string
				int tilde = fmt.IndexOf('~', sc);
				if (tilde < 0)
				{
					sb.Append(fmt.Substring(sc, nc - sc));
					break; // Done -- no more ~
				}
				if (tilde > sc)
				{
					sb.Append(fmt.Substring(sc, tilde - sc));
				}

				// Insert a variable name (or expression)
				index++;
				if (index < numArgs)
				{
					Lisp_Expression expr = exprs._elements.get(index);
					string val = interpreter.getStrValue(expr);
					sb.Append(val);
				}

				// Look for the next piece
				sc = tilde + 2;
			}
			return sb.ToString();
		}
	}

}
