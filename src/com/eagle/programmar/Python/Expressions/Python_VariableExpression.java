// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Subscript;
import com.eagle.programmar.Python.Python_Subscript.Python_SubscrExpr;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Python_VariableExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Python_Variable variable;
	public @S(20) @OPT Python_Subscript subscript;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (subscript != null && subscript.isPresent())
		{
			EagleValue value = interpreter.findSymbol(variable.var.getWhich().toString());
			Python_Subscript.evaluateSubscript(interpreter, value, subscript);
			return;
		}
		
		interpreter.tryToInterpret(variable);
	}
	
	public static Python_VariableExpression newVariableExpression(String name, AbstractExpression subscrExpr, AbstractToken source)
	{
		Python_VariableExpression varExpr = new Python_VariableExpression();
		varExpr.variable = Python_Variable.newVariable(name);

		if (subscrExpr != null)
		{
			varExpr.subscript = new Python_Subscript();
			varExpr.subscript.leftBracket = new PunctuationLeftBracket();
			varExpr.subscript.leftBracket.setPresent(true);
			varExpr.subscript.rightBracket = new PunctuationRightBracket();
			varExpr.subscript.rightBracket.setPresent(true);
			varExpr.subscript.body = new Python_SubscrExpr();
			varExpr.subscript.body.subscr = (Python_Expression) subscrExpr;
		}

		varExpr.setTransformationSource(source);
		return varExpr;
	}
}
