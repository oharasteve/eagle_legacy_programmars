// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2024

package com.eagle.programmar.AWK;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.AWK.AWK_ArgumentList.AWK_MoreArguments;
import com.eagle.programmar.AWK.Expressions.AWK_String;
import com.eagle.programmar.AWK.Terminals.AWK_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleTransformer;

public class AWK_Format
{
	// Handle %d and %s. Super simple ones only for now
	public static String format(EagleInterpreter interpreter,
			SeparatedList<AWK_Expression, PunctuationComma> arguments,
			ArgumentsMetrics metrics)
	{
		int paramCount = arguments.getPrimaryCount();
		if (paramCount == 0) return "";
		String fmt = interpreter.getStrValue(arguments.getPrimaryElement(0));
		if (fmt.indexOf('%') < 0) return fmt;

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = fmt.length();
		int index = 1;
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
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
			if (index < paramCount)
			{
				AWK_Expression expr = arguments.getPrimaryElement(index);
				EagleValue val = interpreter.getEagleValue(expr);
				String piece = val.forceStringValue();
				argTypes.add(val.getType());
				sb.append(piece);
			}
			index++;

			// Look for the next piece
			sc = pct + 2;
		}

		metrics.calledWith(argTypes);
		return sb.toString();
	}

	public static AbstractExpression transform(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			AWK_Expression fmtExpr, TokenList<AWK_MoreArguments> argList, ArrayList<TypeEnum> metrics)
	{
		if (!(fmtExpr.getWhich() instanceof AWK_String))
		{
			throw new RuntimeException("Format must be a literal, not " + fmtExpr.getWhich());
		}
		AWK_String str = (AWK_String) fmtExpr.getWhich();
		AWK_Literal lit = str.literal;
		String fmt = lit.getValue();
		if (fmt.startsWith("\""))
		{
			fmt = fmt.substring(1, fmt.length() - 1);
		}
		if (fmt.endsWith("\\n"))
		{
			fmt = fmt.substring(0, fmt.length() - 2);
		}
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
		for (AWK_MoreArguments more : argList._elements)
		{
			String nextString = fmt.substring(prev, sc);
			if (nextString.length() > 0)
			{
				AbstractExpression nextExpr = generator.newLiteralExpression(nextString, null);
				if (fullExpr == null)
				{
					fullExpr = nextExpr;
				}
				else
				{
					fullExpr = generator.newAppendExpression(fullExpr, nextExpr, null);
				}
			}

			AWK_Expression nextArg = more.expr;
			AbstractExpression nextExpr = transformer.transformExpression(generator, nextArg);
			if (fullExpr == null)
			{
				fullExpr = nextExpr;
			}
			else
			{
				fullExpr = generator.newAppendExpression(fullExpr, nextExpr, null);
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
			fullExpr = generator.newAppendExpression(fullExpr, lastStr, null);
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
