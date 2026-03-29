// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2024

namespace com.eagle.programmar.AWK
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using AWK_MoreArguments = com.eagle.programmar.AWK.AWK_ArgumentList.AWK_MoreArguments;
	using AWK_String = com.eagle.programmar.AWK.Expressions.AWK_String;
	using AWK_Literal = com.eagle.programmar.AWK.Terminals.AWK_Literal;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class AWK_Format
	{
		// Handle %d and %s. Super simple ones only for now
		public static string format(EagleInterpreter interpreter, SeparatedList<AWK_Expression, PunctuationComma> arguments, ArgumentsMetrics metrics)
		{
			int paramCount = arguments.getPrimaryCount();
			if (paramCount == 0)
			{
				return "";
			}
			string fmt = interpreter.getStrValue(arguments.getPrimaryElement(0));
			if (fmt.IndexOf('%') < 0)
			{
				return fmt;
			}

			StringBuilder sb = new StringBuilder();
			int sc = 0;
			int nc = fmt.Length;
			int index = 1;
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
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
				if (index < paramCount)
				{
					AWK_Expression expr = arguments.getPrimaryElement(index);
					EagleValue val = interpreter.getEagleValue(expr);
					string piece = val.forceStringValue();
					argTypes.Add(val.getType());
					sb.Append(piece);
				}
				index++;

				// Look for the next piece
				sc = pct + 2;
			}

			metrics.calledWith(argTypes);
			return sb.ToString();
		}

		public static AbstractExpression transform(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, AWK_Expression fmtExpr, TokenList<AWK_MoreArguments> argList, List<EagleGenerator.TypeEnum> metrics)
		{
			Oper2Types types = null;
			if (metrics != null)
			{
				types = new Oper2Types();
				types._type1 = EagleGenerator.TypeEnum.STRING;
			}

			if (!(fmtExpr.getWhich() is AWK_String))
			{
				throw new Exception("Format must be a literal, not " + fmtExpr.getWhich());
			}
			AWK_String str = (AWK_String) fmtExpr.getWhich();
			AWK_Literal lit = str.literal;
			string fmt = lit.getValue();
			if (fmt.StartsWith("\"", StringComparison.Ordinal))
			{
				fmt = fmt.Substring(1, (fmt.Length - 1) - 1);
			}
			if (fmt.EndsWith("\\n", StringComparison.Ordinal))
			{
				fmt = fmt.Substring(0, fmt.Length - 2);
			}
			int nc = fmt.Length;

			int sc = fmt.IndexOf("%", StringComparison.Ordinal);
			int pctLen = check(fmt, sc, nc);
			if (sc < 0 || pctLen == 0)
			{
				// Nothing to insert in the string
				return generator.newLiteralExpression(fmt, fmtExpr);
			}

			// Have to compose a string out of the pieces
			int prev = 0;
			AbstractExpression fullExpr = null;
			int i = 0;
			foreach (AWK_MoreArguments more in argList._elements)
			{
				string nextString = fmt.Substring(prev, sc - prev);
				if (nextString.Length > 0)
				{
					if (metrics != null)
					{
						types._type2 = EagleGenerator.TypeEnum.STRING;
					}

					AbstractExpression nextExpr = generator.newLiteralExpression(nextString, null);
					if (fullExpr == null)
					{
						fullExpr = nextExpr;
					}
					else
					{
						fullExpr = generator.newAppendExpression(types, fullExpr, nextExpr, null);
					}
				}

				if (metrics != null)
				{
					types._type2 = metrics[i];
				}

				AWK_Expression nextArg = more.expr;
				AbstractExpression nextExpr = transformer.transformExpression(generator, nextArg);
				if (fullExpr == null)
				{
					fullExpr = nextExpr;
				}
				else
				{
					fullExpr = generator.newAppendExpression(types, fullExpr, nextExpr, null);
				}

				prev = sc + 2;
				sc = fmt.IndexOf("%", prev, StringComparison.Ordinal);
				pctLen = check(fmt, sc, nc);
				if (sc < 0 || pctLen == 0)
				{
					break; // Ran out of % insertion points
				}

				i++;
			}
			string lastString = fmt.Substring(prev);
			if (lastString.Length > 0)
			{
				AbstractExpression lastStr = generator.newLiteralExpression(lastString, null);
				fullExpr = generator.newAppendExpression(types, fullExpr, lastStr, null);
			}
			return fullExpr;
		}

		// fmt[sc] is the %. Make sure it is a valid format like %d or %s
		// If so, return 1 for the length after the %. Return 0 if fails.
		private static int check(string fmt, int sc, int nc)
		{
			// Make sure it is %d or %s for now
			if (sc >= 0 && sc + 1 < nc)
			{
				char nextch = fmt[sc + 1];
				if (nextch == 'd' || nextch == 's')
				{
					return 1; // Matches!
				}
			}
			return 0;
		}
	}

}
