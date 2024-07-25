// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Python_SubscriptExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Python_Expression expr = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) @OPT Python_EndOfLine eoln;
	public @S(40) @SYNTAX(Python_Multiline_Syntax.class) Python_SubscrExpr body;
	public @S(50) PunctuationRightBracket rightBracket;

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

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (body.subscriptStep != null && body.subscriptStep.isPresent())
		{
			throw new RuntimeException("Cannot handle range increments yet");
		}
		
		EagleValue value = interpreter.getEagleValue(expr);
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
}
