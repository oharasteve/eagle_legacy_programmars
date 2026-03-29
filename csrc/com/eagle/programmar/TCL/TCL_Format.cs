// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2024

namespace com.eagle.programmar.TCL
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;

	public class TCL_Format
	{
		public static string format(EagleInterpreter interpreter, string fmt)
		{
			if (fmt.IndexOf('[') < 0 && fmt.IndexOf('$') < 0)
			{
				interpreter.pushStr(fmt);
			}

			StringBuilder sb = new StringBuilder();
			int sc = 0;
			int nc = fmt.Length;
			char prev = ' ';
			while (sc < nc)
			{
				char ch = fmt[sc];

				// Check for an escape before the [] or $
				if (prev != '\\')
				{
					string var = null;

					if (ch == '[')
					{
						int secondBracket = fmt.IndexOf(']', sc + 1);
						if (secondBracket < 0)
						{
							throw new Exception("Missing ] in: " + fmt);
						}
						var = fmt.Substring(sc, (secondBracket + 1) - sc); // Leave in the brackets
						sc = secondBracket;
					}
					else if (ch == '$')
					{
						int endDollar = sc + 1;
						while (endDollar < nc)
						{
							// Stop on a space or comma or ....
							if (" ,)".IndexOf(fmt[endDollar]) >= 0)
							{
								break;
							}
							endDollar++;
						}
						var = fmt.Substring(sc, endDollar - sc);
						sc = endDollar - 1;
					}
					else
					{
						sb.Append(ch);
					}

					// Extract a variable name (or expression) and value
					if (!string.ReferenceEquals(var, null))
					{
						TCL_Expression expr = new TCL_Expression();
						if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
						{
							throw new Exception("Unable to parse expression " + var);
						}
						string val = interpreter.getStrValue(expr);
						sb.Append(val);
					}
				}

				sc++;
				prev = ch;
			}
			return sb.ToString();
		}

		public static AbstractExpression compile(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, string fmt, AbstractToken source)
		{
			AbstractExpression result = null;

			int nc = fmt.Length;
			if (nc == 0)
			{
				return generator.newLiteralExpression("", null);
			}

			int sc = 0;
			char prev = ' ';
			StringBuilder piece = new StringBuilder();
			while (sc < nc)
			{
				char ch = fmt[sc];

				// Check for an escape before the [] or $
				string var = null;
				if (prev != '\\')
				{
					if (ch == '[')
					{
						int secondBracket = fmt.IndexOf(']', sc + 1);
						if (secondBracket < 0)
						{
							throw new Exception("Missing ] in: " + fmt);
						}
						var = fmt.Substring(sc, (secondBracket + 1) - sc); // Leave in the brackets
						sc = secondBracket;
					}
					else if (ch == '$')
					{
						int endDollar = sc + 1;
						while (endDollar < nc)
						{
							// Stop on a space or comma or ....
							if (" ,)".IndexOf(fmt[endDollar]) >= 0)
							{
								break;
							}
							endDollar++;
						}
						var = fmt.Substring(sc + 1, endDollar - (sc + 1)); // Skip the leading $
						sc = endDollar - 1;
					}
				}

				if (!string.ReferenceEquals(var, null))
				{
					if (piece.Length > 0)
					{
						AbstractExpression litExpr = generator.newLiteralExpression(piece.ToString(), null);
						if (result == null)
						{
							result = litExpr;
						}
						else
						{
							result = generator.newAdditiveExpression(null, result, EagleGenerator.AdditiveEnum.PLUS, litExpr, null);
						}
						piece = new StringBuilder(); // Start over
					}

					AbstractExpression varExpr = generator.newVariableExpression(var, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, null);
					// Always wrap in a str() function for now
					AbstractExpression strExpr = generator.newStringFunction(null, varExpr, null);
					if (result == null)
					{
						result = varExpr;
					}
					else
					{
						result = generator.newAdditiveExpression(null, result, EagleGenerator.AdditiveEnum.PLUS, strExpr, null);
					}
				}
				else if (ch != '\\' || prev == '\\')
				{
					piece.Append(ch);
				}

				sc++;
				prev = ch;
			}

			if (piece.Length > 0)
			{
				AbstractExpression litExpr = generator.newLiteralExpression(piece.ToString(), null);
				if (result == null)
				{
					result = litExpr;
				}
				else
				{
					result = generator.newAdditiveExpression(null, result, EagleGenerator.AdditiveEnum.PLUS, litExpr, null);
				}
			}

			return result;
		}
	}

}
