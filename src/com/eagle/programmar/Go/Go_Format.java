// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2024

package com.eagle.programmar.Go;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Go.Terminals.Go_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformer;

public class Go_Format
{
	// Handle %d and %s. Super simple ones only for now
	public static String format(EagleInterpreter interpreter,
			SeparatedList<Go_Expression, PunctuationComma> arguments,
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
				Go_Expression expr = arguments.getPrimaryElement(index);
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

	public static AbstractExpression transform(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			SeparatedList<Go_Expression, PunctuationComma> argList, ArrayList<TypeEnum> metrics)
	{
		Oper2Types types = null;
		if (metrics != null)
		{
			types = new Oper2Types();
			types._type1 = TypeEnum.STRING;
		}

		Go_Expression fmtExpr = argList.first();
		if (!(fmtExpr.getWhich() instanceof Go_Literal))
		{
			throw new RuntimeException("Format must be a literal for Printf");
		}
		Go_Literal lit = (Go_Literal) fmtExpr.getWhich();
		String fmt = lit.getValue();
		int nc = fmt.length();
		if (fmt.startsWith("\"") && fmt.endsWith("\"") && nc > 1)
		{
			fmt = fmt.substring(1, nc-1);
			nc = fmt.length();
		}
		if (fmt.endsWith("\\n"))
		{
			fmt = fmt.substring(0, nc-2);
		}
		fmt = fmt.replaceAll("\\\\\"", "\"");
		nc = fmt.length();

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
		for (int i = 1; i < argList.getPrimaryCount(); i++)
		{
			String nextString = fmt.substring(prev, sc);
			if (nextString.length() > 0)
			{
				if (metrics != null)
				{
					types._type2 = TypeEnum.STRING;
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

			Go_Expression nextArg = argList.getPrimaryElement(i);
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
