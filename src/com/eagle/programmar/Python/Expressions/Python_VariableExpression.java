// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.generate.AdditiveEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubscriptEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
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
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Variable variable;
	public @S(20) @OPT @NOSPACE Python_Subscript subscript;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (subscript != null && subscript.isPresent())
		{
			EagleValue value = interpreter.findSymbol(variable.var.getWhich().toString());
			Python_Subscript.evaluateSubscript(interpreter, value, subscript.body);
			return;
		}

		interpreter.tryToInterpret(variable);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String name = variable.var.getWhich().toString();
		if (subscript != null && subscript.isPresent())
		{
			return Python_Subscript.transformSubscript(transformer, generator, variable, subscript.body);
		}
		return generator.newVariableExpression(name,
				SubscriptEnum.FIRST_IS_ZERO, null, this);
	}

	public static Python_Expression generateVariableExpression(String name, SubscriptEnum offset,
			Python_Expression subscrExpr, AbstractToken source)
	{
		Python_VariableExpression varExp = new Python_VariableExpression();
		varExp.variable = Python_Variable.newVariable(name);

		if (subscrExpr != null)
		{
			varExp.subscript = new Python_Subscript();
			varExp.subscript.setPresent(true);
			varExp.subscript.leftBracket = new PunctuationLeftBracket();
			varExp.subscript.rightBracket = new PunctuationRightBracket();
			varExp.subscript.body = new Python_SubscrExpr();

			if (offset == SubscriptEnum.FIRST_IS_ONE)
			{
				Python_Expression one = Python_Number.generateNumberExpression("1", source);
				Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
				Python_Expression minusOne = Python_Additive_Expression.generateAdditive(types, subscrExpr,
						AdditiveEnum.MINUS, one, source);
				varExp.subscript.body.subscr = minusOne;
			}
			else
			{
				varExp.subscript.body.subscr = subscrExpr;
			}

			varExp.subscript.body.subscr.setPresent(true);
		}

		varExp.setTransformationSource(source);
		return Python_Generator.wrapExpression(varExp);
	}
}
