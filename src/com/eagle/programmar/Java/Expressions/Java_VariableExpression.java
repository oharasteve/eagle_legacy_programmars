// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Subscript;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Java_VariableExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Java_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}

	public static Java_VariableExpression newVariableExpression(String name, AbstractExpression subscrExpr, AbstractToken source)
	{
		Java_VariableExpression varExpr = new Java_VariableExpression();
		varExpr.variable = Java_Variable.newVariable(name);

		if (subscrExpr != null)
		{
			Java_Subscript subscript = new Java_Subscript();
			subscript.leftBracket = new PunctuationLeftBracket();
			subscript.expr = (Java_Expression) subscrExpr;
			subscript.expr.setPresent(true);
			subscript.rightBracket = new PunctuationRightBracket();

			varExpr.variable.subscript = new TokenList<Java_Subscript>();
			varExpr.variable.subscript.addToken(subscript);
		}

		varExpr.setTransformationSource(source);
		return varExpr;
	}
}
