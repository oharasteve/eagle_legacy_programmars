// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.generate.Expressions.Eagle_Generate_VarExpr;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Subscript;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Java_VariableExpression extends PrimaryOperator
		implements EagleRunnable, Eagle_Generate_VarExpr<Java_Expression>
{
	public @S(10) Java_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}

	@Override
	public Java_Expression generateVarExpr(String name, Java_Expression subscrExpr, AbstractToken source)
	{
		this.variable = Java_Variable.newVariable(name);

		if (subscrExpr != null)
		{
			Java_Subscript subscript = new Java_Subscript();
			subscript.leftBracket = new PunctuationLeftBracket();
			subscript.expr = subscrExpr;
			subscript.expr.setPresent(true);
			subscript.rightBracket = new PunctuationRightBracket();

			this.variable.subscript = new TokenList<Java_Subscript>();
			this.variable.subscript.addToken(subscript);
		}

		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}
