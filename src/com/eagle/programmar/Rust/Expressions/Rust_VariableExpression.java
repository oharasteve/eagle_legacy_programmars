// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		return generator.newVariableExpression(variable.var.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, null, this);
	}

	public static Rust_Expression generateVariableExpression(String name, SubscriptEnum offset,
			Rust_Expression subscrExpr, AbstractToken source)
	{
		Rust_VariableExpression var = new Rust_VariableExpression();
		var.variable = Rust_Variable.generateVariable(name);
		var.setTransformationSource(source);
		Rust_Expression theExpr = Rust_Generator.wrapExpression(var);
		if (subscrExpr == null)
		{
			return theExpr;
		}
		
		if (offset == SubscriptEnum.IT_IS_A_HASHMAP)
		{
			throw new RuntimeException("Need to implement HashMap");
//			Rust_MethodInvocation invoke = new Rust_MethodInvocation();
//			Rust_Variable var = Rust_Variable.newVariable(name + ".get");
//			ArrayList<Rust_Expression> args = new ArrayList<Rust_Expression>();
//			args.add(subscrExpr);
//			return invoke.generateInvocation(var, args, source);
		}

		Rust_Expression subExpr = null;
		if (offset == SubscriptEnum.FIRST_IS_ONE)
		{
			if (subscrExpr.getWhich() instanceof Rust_AdditiveExpression)
			{
				Rust_AdditiveExpression addExpr = (Rust_AdditiveExpression) subscrExpr.getWhich();
				if (addExpr.operator.getValue().equals("+"))
				{
					if (addExpr.right.getWhich() instanceof Rust_Number)
					{
						Rust_Number rightNum = (Rust_Number) addExpr.right.getWhich();
						if (rightNum.getValue().equals("1"))
						{
							// Gonna get + 1 - 1 if we don't act now ....
							subExpr = addExpr.left;
						}
					}
				}
			}

			if (subExpr == null)
			{
				Rust_Expression one = Rust_Number.createExpression(1);
				Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
				subExpr = Rust_AdditiveExpression.generateAdditive(types, subscrExpr,
						AdditiveEnum.MINUS, one, source);
			}
		}
		
		if (subExpr == null)
		{
			subExpr = subscrExpr;
		}
		
//		Rust_Type usize = Rust_Type.newPrimitiveType("usize");
//		subExpr = Rust_AsExpression.generateAsExpr(subExpr, usize, source);
		return Rust_SubscriptExpression.generateSubscriptExpression(theExpr, subExpr,
				SubstringSCEnum.FIRST_CHAR_IS_ZERO, SubstringECEnum.TO_END, null, false, source);
	}
}
