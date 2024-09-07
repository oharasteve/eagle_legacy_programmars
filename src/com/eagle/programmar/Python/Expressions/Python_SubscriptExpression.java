// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Subscript;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;

public class Python_SubscriptExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Python_Expression expr = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_Subscript subscr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		Python_Subscript.evaluateSubscript(interpreter, value, subscr);
	}
	
	public static Python_SubscriptExpression generateExpression(AbstractExpression theExpr, AbstractExpression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, AbstractExpression ecOrnc, AbstractToken source)
	{
		Python_SubscriptExpression expr = new Python_SubscriptExpression();
		expr.expr = (Python_Expression) theExpr;
		expr.subscr = Python_Subscript.generateExpression(sc, whichSC, whichEC, ecOrnc, source);
		expr.setTransformationSource(source);
		return expr;
	}
}
