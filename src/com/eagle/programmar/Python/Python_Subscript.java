// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2024

package com.eagle.programmar.Python;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
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
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;

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
	
	public static void evaluateSubscript(EagleInterpreter interpreter, EagleValue value, Python_Subscript subscr)
	{
		Python_SubscrExpr body = subscr.body;
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
	
	public static Python_Subscript generateExpression(AbstractExpression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, AbstractExpression ecOrnc, AbstractToken source)
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
			Python_Expression one = Python_Generator.wrapExpression(Python_Number.generateExpression("1", source));
			Python_Additive_Expression scMinusOne = Python_Additive_Expression.generateAdditive(sc, AdditiveEnum.MINUS, one, source);
			subscr.body.subscr = Python_Generator.wrapExpression(scMinusOne);
			subscr.body.subscr.setPresent(true);
			break;
		}

		switch (whichEC)
		{
		case GIVEN_EC:
			subscr.body.subscriptStop = new Python_ColonSubscript();
			subscr.body.subscriptStop.colon = new PunctuationColon();
			subscr.body.subscriptStop.setPresent(true);
			subscr.body.subscriptStop.expr = (Python_Expression) ecOrnc;
			subscr.body.subscriptStop.expr.setPresent(true);
			break;
		case GIVEN_NC:
			subscr.body.subscriptStop = new Python_ColonSubscript();
			subscr.body.subscriptStop.colon = new PunctuationColon();
			subscr.body.subscriptStop.setPresent(true);
			Python_Additive_Expression scPlusNc = Python_Additive_Expression.generateAdditive(subscr.body.subscr, AdditiveEnum.PLUS, ecOrnc, source);
			subscr.body.subscriptStop.expr = Python_Generator.wrapExpression(scPlusNc);
			subscr.body.subscriptStop.expr.setPresent(true);
			break;
		case GIVEN_NEITHER:
			subscr.body.subscriptStop = null;
			break;
		}
		subscr.setTransformationSource(source);
		return subscr;
	}
}
