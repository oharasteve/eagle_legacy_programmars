// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.generate.EagleGenerator.AdditiveEnum;
import com.eagle.generate.EagleGenerator.SubscriptEnum;
import com.eagle.generate.Expressions.Eagle_Generate_VarExpr;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Subscript;
import com.eagle.programmar.Python.Python_Subscript.Python_SubscrExpr;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Terminals.Python_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Python_VariableExpression extends PrimaryOperator
		implements EagleRunnable, Eagle_Generate_VarExpr<Python_Expression>
{
	public @S(10) Python_Variable variable;
	public @S(20) @OPT @NOSPACE Python_Subscript subscript;

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
	public Python_Expression generateVarExpr(String name, SubscriptEnum offset,
			Python_Expression subscrExpr, AbstractToken source)
	{
		this.variable = Python_Variable.newVariable(name);

		if (subscrExpr != null)
		{
			this.subscript = new Python_Subscript();
			this.subscript.setPresent(true);
			this.subscript.leftBracket = new PunctuationLeftBracket();
			this.subscript.rightBracket = new PunctuationRightBracket();
			this.subscript.body = new Python_SubscrExpr();
			
			if (offset == SubscriptEnum.FIRST_IS_ONE)
			{
				Python_Number num = new Python_Number();
				Python_Expression one = Python_Generator.wrapExpression(num.generateNumber("1", source));
				Python_Additive_Expression addExp = new Python_Additive_Expression();
				Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
				Python_Expression minusOne = addExp.generateAdditive(types, subscrExpr,
						AdditiveEnum.MINUS, one, source);
				this.subscript.body.subscr = minusOne;
			}
			else
			{
				this.subscript.body.subscr = subscrExpr;
			}

			this.subscript.body.subscr.setPresent(true);
		}

		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}
