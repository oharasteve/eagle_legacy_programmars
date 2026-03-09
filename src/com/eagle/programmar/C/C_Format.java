// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.C.Expressions.C_Literals;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformer;

public class C_Format
{
	// Handle %d and %s. Super simple ones only for now
	public static String format(EagleInterpreter interpreter,
			SeparatedList<C_Expression, PunctuationComma> args,
			ArrayList<String> argTypes)
	{
		String fmt = interpreter.getStrValue(args.first());
		fmt = fmt.replaceAll("\\\\n", "");
		if (fmt.indexOf('%') < 0) return fmt;

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = fmt.length();
		int index = 0;
		int numArgs = args.getPrimaryCount();
		while (sc < nc)
		{
			// Pull in a text string
			int pct = fmt.indexOf('%', sc);
			if (pct < 0)
			{
				sb.append(fmt.substring(sc, nc));
				break; // Done -- no more %
			}
			if (pct > sc)
			{
				sb.append(fmt.substring(sc, pct));
			}

			// Insert a variable name (or expression)
			index++;
			if (index < numArgs)
			{
				C_Expression expr = args.getPrimaryElement(index);
				EagleValue val = interpreter.getEagleValue(expr);
				String piece = val.forceStringValue();
				argTypes.add(val.typeName());
				sb.append(piece);
			}

			// Look for the next piece
			sc = pct + 2;
		}
		return sb.toString();
	}

	public static AbstractExpression transform(EagleTransformer transformer, EagleGenerator generator,
			SeparatedList<C_Expression, PunctuationComma> args, ArrayList<String> metrics)
	{
		Oper2Types types = null;
		if (metrics != null)
		{
			types = new Oper2Types();
			types._type1 = EagleString.STRING;
		}

		C_Expression fmtExpr = args.first();
		if (!(fmtExpr.getWhich() instanceof C_Literals))
		{
			throw new RuntimeException("Format must be a literal for Fprintf");
		}
		C_Literals lits = (C_Literals) fmtExpr.getWhich();
		if (lits.literals.size() > 1)
		{
			throw new RuntimeException("Format must be just a single literal");
		}
		String fmt = lits.literals.first().getValue();
		if (fmt.startsWith("\""))
		{
			fmt = fmt.substring(1, fmt.length() - 1);
		}
		if (fmt.endsWith("\\n"))
		{
			fmt = fmt.substring(0, fmt.length() - 2);
		}
		fmt = fmt.replaceAll("\\\\\"", "\\\"");
		int nc = fmt.length();

		int sc = fmt.indexOf("%");
		int pctLen = check(fmt, sc, nc);
		if (sc < 0 || pctLen == 0)
		{
			// Nothing to insert in the string
			return generator.newLiteralExpression(fmt, fmtExpr);
		}

		// Have to compose a string out of the pieces
		int prev = 0;
		AbstractExpression fullExpr = null;
		int numArgs = args.getPrimaryCount();
		for (int i = 1; i <= numArgs; i++)
		{
			String nextString = fmt.substring(prev, sc);
			if (nextString.length() > 0)
			{
				if (metrics != null)
				{
					types._type2 = EagleString.STRING;
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
				types._type2 = metrics.get(i - 1);
			}

			C_Expression nextArg = args.getPrimaryElement(i);
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
			sc = fmt.indexOf("%", prev);
			pctLen = check(fmt, sc, nc);
			if (sc < 0 || pctLen == 0) break; // Ran out of % insertion points
		}
		String lastString = fmt.substring(prev);
		if (lastString.length() > 0)
		{
			AbstractExpression lastStr = generator.newLiteralExpression(lastString, null);
			fullExpr = generator.newAppendExpression(types, fullExpr, lastStr, null);
		}
		return fullExpr;
	}

	// fmt[sc] is the %. Make sure it is a valid format like %d or %s
	// If so, return 1 for the length after the %. Return 0 if fails.
	private static int check(String fmt, int sc, int nc)
	{
		// Make sure it is %d or %s for now
		if (sc >= 0 && sc + 1 < nc)
		{
			char nextch = fmt.charAt(sc + 1);
			if (nextch == 'd' || nextch == 's')
			{
				return 1; // Matches!
			}
		}
		return 0;
	}
}
