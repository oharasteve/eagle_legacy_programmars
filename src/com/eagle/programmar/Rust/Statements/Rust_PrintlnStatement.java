// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Rust_PrintlnStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Rust_Keyword PRINTLN = new Rust_Keyword("println");
	public @S(20) Rust_Punctuation bang = new Rust_Punctuation("!");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) SeparatedList<Rust_Expression, PunctuationComma> items;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) @OPT PunctuationSemicolon semicolon;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fmt = interpreter.getStrValue(items.getPrimaryElement(0));
		int sc = fmt.indexOf("{}");
		if (sc < 0)
		{
			// Nothing to insert in the string
			System.out.println(fmt);
		}
		else
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, PRINTLN.getValue(), PRINTLN);
			}
			ArrayList<String> argTypes = new ArrayList<String>();

			StringBuffer result = new StringBuffer();
			int prev = 0;
			for (int i = 1; i < items.getPrimaryCount(); i++)
			{
				result.append(fmt.substring(prev, sc));
				
				EagleValue val = interpreter.getEagleValue(items.getPrimaryElement(i));
				String piece = val.forceStringValue();
				argTypes.add(val.typeName());
				System.out.print(result);

				result.append(piece);
				prev = sc + 2;
				sc = fmt.indexOf("{}", prev);
				if (sc < 0) break; // Ran out of {} insertion points
			}
			result.append(fmt.substring(prev));
			_metrics.calledWith(argTypes);
			System.out.println(result.toString());
		}
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		Oper2Types types = null;
		// Pick up metrics, if known
		ArrayList<String> metrics = transformer.findArgumentsMetric(PRINTLN);
		if (metrics != null)
		{
			types = new Oper2Types();
			types._type2 = EagleString.STRING;
		}

		Rust_Expression fmtExpr = items.first();
		if (! (fmtExpr.getWhich() instanceof Rust_Literal))
		{
			throw new RuntimeException("Format must be a literal for print!");
		}
		Rust_Literal lit = (Rust_Literal) fmtExpr.getWhich();
		String fmt = lit.getValue();
		if (fmt.startsWith("\""))
		{
			fmt = fmt.substring(1, fmt.length()-1);
		}
		
		int sc = fmt.indexOf("{}");
		if (sc < 0)
		{
			// Nothing to insert in the string
			AbstractExpression newExpr = generator.newLiteralExpression(fmt, fmtExpr);
			return generator.newPrintStatement(newExpr, true, this);
		}

		// Have to compose a string out of the pieces
		int prev = 0;
		AbstractExpression fullExpr = null;
		for (int i = 1; i < items.getPrimaryCount(); i++)
		{
			String nextString = fmt.substring(prev, sc);
			if (nextString.length() > 0)
			{
				if (metrics != null)
				{
					types._type1 = types._type2;
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
				types._type1 = types._type2;
				types._type2 = metrics.get(i - 1);
			}
			
			Rust_Expression nextArg = items.getPrimaryElement(i);
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
			sc = fmt.indexOf("{}", prev);
			if (sc < 0) break; // Ran out of {} insertion points
		}
		String lastString = fmt.substring(prev);
		if (lastString.length() > 0)
		{
			AbstractExpression lastStr = generator.newLiteralExpression(lastString, null);
			fullExpr = generator.newAppendExpression(types, fullExpr, lastStr, null);
		}

		return generator.newPrintStatement(fullExpr, true, this);
	}
}
