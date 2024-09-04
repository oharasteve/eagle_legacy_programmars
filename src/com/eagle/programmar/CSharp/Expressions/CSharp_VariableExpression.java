// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Subscript;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class CSharp_VariableExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CSharp_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}
	
	public static CSharp_VariableExpression newVariableExpression(String name, AbstractExpression subscrExpr, AbstractToken source)
	{
		CSharp_VariableExpression varExpr = new CSharp_VariableExpression();
		varExpr.variable = CSharp_Variable.newVariable(name);

		if (subscrExpr != null)
		{
			CSharp_Subscript subscript = new CSharp_Subscript();
			subscript.leftBracket = new PunctuationLeftBracket();
			subscript.expr = (CSharp_Expression) subscrExpr;
			subscript.expr.setPresent(true);
			subscript.rightBracket = new PunctuationRightBracket();

			varExpr.variable.subscript = new TokenList<CSharp_Subscript>();
			varExpr.variable.subscript.addToken(subscript);
		}

		varExpr.setTransformationSource(source);
		return varExpr;
	}
}
