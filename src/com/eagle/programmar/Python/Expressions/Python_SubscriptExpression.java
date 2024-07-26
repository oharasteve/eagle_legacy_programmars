// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Subscript;
import com.eagle.programmar.Python.Python_Subscript.Python_SubscrExpr;
import com.eagle.tokens.PrecedenceOperator;

public class Python_SubscriptExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Python_Expression expr = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_Subscript subscr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Python_SubscrExpr body = subscr.body;
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
