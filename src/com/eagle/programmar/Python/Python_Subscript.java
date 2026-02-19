// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2024

package com.eagle.programmar.Python;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformer;

public class Python_Subscript extends TokenSequence
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) @OPT Python_EndOfLine eoln;
	public @S(30) @SYNTAX(Python_Multiline_Syntax.class) @NOSPACE Python_SubscrExpr body;
	public @S(40) @NOSPACE PunctuationRightBracket rightBracket;

	public static class Python_SubscrExpr extends TokenSequence
	{
		public @S(10) @OPT Python_Expression subscr;
		public @S(20) @OPT Python_ColonSubscript subscriptStop;
		public @S(30) @OPT Python_ColonSubscript subscriptStep;
	}

	public static class Python_ColonSubscript extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) @OPT Python_EndOfLine eoln;
		public @S(30) @OPT Python_Expression expr;
	}

	public static void evaluateSubscript(EagleInterpreter interpreter, EagleValue value, Python_SubscrExpr body)
	{
		if (body.subscriptStep != null && body.subscriptStep.isPresent())
		{
			throw new RuntimeException("Cannot handle range increments yet");
		}

		if (value.isArray())
		{
			if (body.subscriptStop != null && body.subscriptStop.isPresent())
			{
				throw new RuntimeException("Cannot handle array ranges yet");
			}
			EagleArray array = (EagleArray) value;
			int sub = interpreter.getIntValue(body.subscr);
			interpreter.pushEagleValue(array.getArrayValue().get(sub));
		}
		else
		{
			String str = value.forceStringValue();

			int start = 0;
			int stop = 0;
			if (body.subscr != null && body.subscr.isPresent())
			{
				start = interpreter.getIntValue(body.subscr);
				stop = start + 1;
			}
			if (body.subscriptStop != null && body.subscriptStop.isPresent())
			{
				stop = str.length();
				if (body.subscriptStop.expr != null && body.subscriptStop.expr.isPresent())
				{
					stop = interpreter.getIntValue(body.subscriptStop.expr);
				}
			}
			interpreter.pushStr(str.substring(start, stop));
		}
	}

	public static AbstractExpression transformSubscript(EagleTransformer transformer,
			EagleGenerator generator, Python_Variable var, Python_SubscrExpr body)
	{
		String name = var.var.getWhich().toString();

		if (body.subscriptStop == null || !body.subscriptStop.isPresent())
		{
			// Just a regular array access
			AbstractExpression subExpr = transformer.transformExpression(generator, body.subscr);
			return generator.newVariableExpression(name, SubscriptEnum.FIRST_IS_ZERO, subExpr, body);
		}

		if (body.subscriptStep != null && body.subscriptStep.isPresent())
		{
			throw new RuntimeException("Cannot handle subscripts with steps: " + body.subscriptStep);
		}
		AbstractExpression theStr = generator.newVariableExpression(name,
				SubscriptEnum.FIRST_IS_ZERO, null, body);

		boolean hasStart = body.subscr != null && body.subscr.isPresent();
		boolean hasColon = body.subscriptStop != null && body.subscriptStop.isPresent();
		boolean hasStop = hasColon && body.subscriptStop.expr != null && body.subscriptStop.expr.isPresent();

		AbstractExpression startExpr = null;
		AbstractExpression stopExpr = null;
		SubstringECEnum whichEC = SubstringECEnum.GIVEN_EC_PLUS_ONE;
		if (hasStart)
		{
			startExpr = transformer.transformExpression(generator, body.subscr);
			if (hasStop)
			{
				// Case I: a[1:2]
				stopExpr = transformer.transformExpression(generator, body.subscriptStop.expr);
			}
			else if (hasColon)
			{
				// Case II: a[1:]
				stopExpr = generator.newLengthFunction(theStr, null);
			}
//			else
//			{
//				// Case III: a[1]
//				stopExpr = generator.newNumberExpression("1", null);
//				whichEC = SubstringECEnum.GIVEN_NC;
//			}
		}
		else if (hasStop)
		{
			// Case IV: a[:2]
			startExpr = generator.newNumberExpression("0", null);
			stopExpr = transformer.transformExpression(generator, body.subscriptStop.expr);
		}
		else
		{
			throw new RuntimeException("Subscripts need either a start or stop (or both): " + body);
		}

		return generator.newSubstringFunction(theStr, startExpr, SubstringSCEnum.FIRST_CHAR_IS_ZERO,
				whichEC, stopExpr, true, body);
	}

	public static Python_Subscript generateExpression(AbstractExpression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, AbstractExpression ecOrnc,
			AbstractToken source)
	{
		Python_Subscript subscr = new Python_Subscript();
		subscr.leftBracket = new PunctuationLeftBracket();
		subscr.body = new Python_SubscrExpr();
		subscr.body.subscriptStep = null;
		subscr.rightBracket = new PunctuationRightBracket();

		switch (whichSC)
		{
		case FIRST_CHAR_IS_ZERO:
			subscr.body.subscr = (Python_Expression) sc;
			subscr.body.subscr.setPresent(true);
			break;
		case FIRST_CHAR_IS_ONE:
			Python_Expression one = Python_Number.generateNumberExpression("1", source);
			Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
			Python_Expression scMinusOne = Python_Additive_Expression.generateAdditive(types,
					(Python_Expression) sc, AdditiveEnum.MINUS, one, source);
			subscr.body.subscr = scMinusOne;
			subscr.body.subscr.setPresent(true);
			break;
		}

		subscr.body.subscriptStop = new Python_ColonSubscript();
		subscr.body.subscriptStop.setPresent(true);
		subscr.body.subscriptStop.colon = new PunctuationColon();
		switch (whichEC)
		{
		case GIVEN_EC:
			if (ecOrnc != null)
			{
				switch (whichSC)
				{
				case FIRST_CHAR_IS_ZERO:
					Python_Expression one = Python_Number.generateNumberExpression("1", source);
					Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
					Python_Expression ecPlusOne = Python_Additive_Expression.generateAdditive(types,
							(Python_Expression) ecOrnc, AdditiveEnum.PLUS, one, source);
					subscr.body.subscriptStop.expr = ecPlusOne;
					break;
				case FIRST_CHAR_IS_ONE:
					subscr.body.subscriptStop.expr = (Python_Expression) ecOrnc;
					break;
				}
				subscr.body.subscriptStop.expr.setPresent(true);
			}
			break;
		case GIVEN_EC_PLUS_ONE:
			if (ecOrnc != null)
			{
				subscr.body.subscriptStop.expr = (Python_Expression) ecOrnc;
				subscr.body.subscriptStop.expr.setPresent(true);
			}
			break;
		case GIVEN_NC:
			Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
			Python_Expression scPlusNc = Python_Additive_Expression.generateAdditive(types,
					subscr.body.subscr, AdditiveEnum.PLUS, (Python_Expression) ecOrnc, source);
			subscr.body.subscriptStop.expr = scPlusNc;
			subscr.body.subscriptStop.expr.setPresent(true);
			break;
		case GIVEN_NEITHER:
			break;
		}

		subscr.setTransformationSource(source);
		return subscr;
	}
}
