// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2024

namespace com.eagle.programmar.Eaglish
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Eaglish_Format
	{
		public static string format(EagleInterpreter interpreter, string txt)
		{
			if (txt.IndexOf('^') < 0 && txt.IndexOf('\\') < 0)
			{
				interpreter.pushStr(txt);
			}

			StringBuilder sb = new StringBuilder();
			int sc = 0;
			int nc = txt.Length;
			char prev = ' ';
			while (sc < nc)
			{
				char ch = txt[sc];

				if (prev == '\\')
				{
					sb.Append(ch); // This character, no matter what it is
				}
				else if (ch == '\\')
				{
					// Don't do anything with this, it escapes the next character
				}
				else if (ch == '^')
				{
					// Extract a variable name (or expression) and value
					int second = txt.IndexOf('^', sc + 1);
					if (second < 0)
					{
						throw new Exception("Missing ^ in " + txt);
					}
					string var = txt.Substring(sc + 1, second - (sc + 1));
					Eaglish_Expression expr = new Eaglish_Expression();
					if (!interpreter._parser.parseLine(var, interpreter._lang, expr))
					{
						throw new Exception("Unable to parse expression " + var);
					}
					string val = interpreter.getStrValue(expr);
					sb.Append(val);

					// Get past the second ^
					sc = second;
				}
				else
				{
					sb.Append(ch); // Just a plain old character, save it
				}

				sc++;
				prev = ch;
			}
			return sb.ToString();
		}

		public static AbstractExpression compile(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, string fmt, AbstractToken source)
		{
			AbstractExpression result = null;

			int nc = fmt.Length;
			if (nc == 0)
			{
				return generator.newLiteralExpression("", null);
			}

			int sc = 0;
			while (sc < nc)
			{
				// Pull in a text string
				int nextInsertion = fmt.IndexOf("^", sc, StringComparison.Ordinal);
				int ec = nextInsertion;
				if (nextInsertion < 0)
				{
					ec = nc; // No more ^, go all the way to the end
				}

				if (ec > sc)
				{
					// Grab next literal piece
					AbstractExpression piece1 = generator.newLiteralExpression(fmt.Substring(sc, ec - sc), null);
					if (result == null)
					{
						result = piece1;
					}
					else
					{
						result = generator.newAdditiveExpression(null, result, EagleGenerator.AdditiveEnum.PLUS, piece1, null);
					}
				}

				if (nextInsertion < 0)
				{
					break; // Done -- no more ^
				}

				// Pick out the variable name, like ^ok^
				int endInsertion = fmt.IndexOf("^", nextInsertion + 1, StringComparison.Ordinal);
				if (endInsertion < 0)
				{
					throw new Exception("Missing second ^ following ^");
				}
				string text = fmt.Substring(nextInsertion + 1, endInsertion - (nextInsertion + 1));
				Eaglish_Expression expr = new Eaglish_Expression();
				if (!generator._parser.parseLine(text, generator._parser._parser.getLanguage(), expr))
				{
					throw new Exception("Unable to parse expression " + expr);
				}
				AbstractExpression newExpr = transformer.transformExpression(generator, expr);
				// Always wrap in a str() function for now
				AbstractExpression strExpr = generator.newStringFunction(null, newExpr, null);
				if (result == null)
				{
					result = strExpr;
				}
				else
				{
					result = generator.newAdditiveExpression(null, result, EagleGenerator.AdditiveEnum.PLUS, strExpr, null);
				}
				sc = endInsertion + 1;
			}

			return result;
		}
	}

}
