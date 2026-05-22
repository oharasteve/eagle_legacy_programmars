// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2024

package com.eagle.programmar.Rust;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Rust.Terminals.Rust_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformer;

public class Rust_Format
{
	public static String format(EagleInterpreter interpreter,
			SeparatedList<Rust_Expression, PunctuationComma> argList,
			ArgumentsMetrics metrics)
	{
		String fmt = interpreter.getStrValue(argList.first());
		if (fmt.indexOf("{}") < 0) return fmt;

		StringBuffer sb = new StringBuffer();
		int sc = 0;
		int nc = fmt.length();
		int index = 0;
		int numArgs = argList.getPrimaryCount();
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		while (sc < nc)
		{
			// Pull in a text string
			int nxt = fmt.indexOf("{}", sc);
			if (nxt < 0)
			{
				sb.append(fmt.substring(sc, nc));
				break; // Done -- no more {}
			}
			if (nxt > sc)
			{
				sb.append(fmt.substring(sc, nxt));
			}

			// Insert a variable name (or expression)
			index++;
			if (index < numArgs)
			{
				Rust_Expression expr = argList.getPrimaryElement(index);
				EagleValue val = interpreter.getEagleValue(expr);
				String piece = val.forceStringValue();
				argTypes.add(val.getType());
				sb.append(piece);
			}

			// Look for the next piece
			sc = nxt + 2;
		}

		metrics.calledWith(argTypes);
		return sb.toString();
	}

	public static AbstractExpression compile(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			SeparatedList<Rust_Expression, PunctuationComma> argList, ArrayList<TypeEnum> metrics)
	{
		Oper2Types types = null;
		if (metrics != null)
		{
			types = new Oper2Types();
			types._type1 = TypeEnum.STRING;
		}

		Rust_Expression fmtExpr = argList.first();
		if (!(fmtExpr.getWhich() instanceof Rust_Literal))
		{
			throw new RuntimeException("Format must be a literal for print!");
		}
		Rust_Literal lit = (Rust_Literal) fmtExpr.getWhich();
		String fmt = lit.getValue();
		if (fmt.startsWith("\""))
		{
			fmt = fmt.substring(1, fmt.length() - 1).replaceAll("\\\\\"", "\"");
		}

		int sc = fmt.indexOf("{}");
		if (sc < 0)
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
					fullExpr = generator.newAppendExpression(fullExpr, nextExpr, null);
				}
			}

			if (metrics != null)
			{
				types._type2 = metrics.get(i - 1);
			}

			Rust_Expression nextArg = argList.getPrimaryElement(i);
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
			sc = fmt.indexOf("{}", prev);
			if (sc < 0) break; // Ran out of {} insertion points
		}
		String lastString = fmt.substring(prev);
		if (lastString.length() > 0)
		{
			AbstractExpression lastStr = generator.newLiteralExpression(lastString, null);
			fullExpr = generator.newAppendExpression(fullExpr, lastStr, null);
		}
		return fullExpr;
	}
}
