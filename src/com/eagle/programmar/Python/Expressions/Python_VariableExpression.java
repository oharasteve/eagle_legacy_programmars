// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.generate.Expressions.Eagle_Generate_VarExpr;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Subscript;
import com.eagle.programmar.Python.Python_Subscript.Python_SubscrExpr;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Python_VariableExpression extends PrimaryOperator
		implements EagleRunnable, Eagle_Generate_VarExpr<Python_Expression>
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
	
	@Override
	public Python_Expression generateVarExpr(String name,
			Python_Expression subscrExpr, AbstractToken source)
	{
		this.variable = Python_Variable.newVariable(name);

		if (subscrExpr != null)
		{
			this.subscript = new Python_Subscript();
			this.subscript.leftBracket = new PunctuationLeftBracket();
			this.subscript.leftBracket.setPresent(true);
			this.subscript.rightBracket = new PunctuationRightBracket();
			this.subscript.rightBracket.setPresent(true);
			this.subscript.body = new Python_SubscrExpr();
			this.subscript.body.subscr = subscrExpr;
		}

		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}
