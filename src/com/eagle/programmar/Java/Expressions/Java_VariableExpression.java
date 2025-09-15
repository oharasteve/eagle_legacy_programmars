// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Subscript;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;

public class Java_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression subscript = null;
		if (variable.subscript != null && variable.subscript.size() > 0)
		{
			Java_Subscript first = variable.subscript.first();
			subscript = transformer.transformExpression(generator, first.expr);
		}
		AbstractToken which = variable.firstId.getWhich();
		if (! (which instanceof Java_Identifier_Reference))
		{
			throw new RuntimeException("Cannot handle variable: " + which);
		}
		Java_Identifier_Reference id = (Java_Identifier_Reference) which;
			return generator.newVariableExpression(id.getValue(),
					SubscriptEnum.FIRST_IS_ZERO, subscript, this);
	}

	public Java_Expression generateVarExpr(String name, SubscriptEnum offset,
			Java_Expression subscrExpr, AbstractToken source)
	{
		this.variable = Java_Variable.newVariable(name);
		this.setTransformationSource(source);

		if (subscrExpr != null)
		{
			if (offset == SubscriptEnum.IT_IS_A_HASHMAP)
			{	
				Java_MethodInvocation invoke = new Java_MethodInvocation();
				Java_Variable var = Java_Variable.newVariable(name + ".get");
				ArrayList<Java_Expression> args = new ArrayList<Java_Expression>();
				args.add(subscrExpr);
				return invoke.generateInvocation(var, args, source);
			}
			
			Java_Subscript subscript = new Java_Subscript();
			subscript.leftBracket = new PunctuationLeftBracket();
			subscript.rightBracket = new PunctuationRightBracket();
			
			if (offset == SubscriptEnum.FIRST_IS_ONE)
			{
				Java_Number num = new Java_Number();
				Java_Expression one = Java_Generator.wrapExpression(num.generateNumber("1", source));
				Java_AdditiveExpression addExp = new Java_AdditiveExpression();
				Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
				Java_Expression minusOne = addExp.generateAdditive(types, subscrExpr,
						AdditiveEnum.MINUS, one, source);
				subscript.expr = minusOne;
			}
			else
			{
				subscript.expr = subscrExpr;
			}
			subscript.expr.setPresent(true);

			this.variable.subscript = new TokenList<Java_Subscript>();
			this.variable.subscript.addToken(subscript);
		}

		return Java_Generator.wrapExpression(this);
	}
}
