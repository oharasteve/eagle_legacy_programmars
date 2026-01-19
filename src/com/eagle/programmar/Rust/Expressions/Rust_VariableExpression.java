// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
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
			EagleGenerator generator)
	{
		return generator.newVariableExpression(variable.var.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, null, this);
	}

	public Rust_Expression generateVarExpr(String name, SubscriptEnum offset,
			Rust_Expression subscrExpr, AbstractToken source)
	{
		this.variable = Rust_Variable.newVariable(name);
		this.setTransformationSource(source);

//		if (subscrExpr != null)
//		{
//			if (offset == SubscriptEnum.IT_IS_A_HASHMAP)
//			{
//				Rust_MethodInvocation invoke = new Rust_MethodInvocation();
//				Rust_Variable var = Rust_Variable.newVariable(name + ".get");
//				ArrayList<Rust_Expression> args = new ArrayList<Rust_Expression>();
//				args.add(subscrExpr);
//				return invoke.generateInvocation(var, args, source);
//			}
//
//			Rust_Subscript subscript = new Rust_Subscript();
//			subscript.leftBracket = new PunctuationLeftBracket();
//			subscript.rightBracket = new PunctuationRightBracket();
//
//			if (offset == SubscriptEnum.FIRST_IS_ONE)
//			{
//				Rust_Number num = new Rust_Number();
//				Rust_Expression one = Rust_Generator.wrapExpression(num.generateNumber("1", source));
//				Rust_AdditiveExpression addExp = new Rust_AdditiveExpression();
//				Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
//				Rust_Expression minusOne = addExp.generateAdditive(types, subscrExpr,
//						AdditiveEnum.MINUS, one, source);
//				subscript.expr = minusOne;
//			}
//			else
//			{
//				subscript.expr = subscrExpr;
//			}
//			subscript.expr.setPresent(true);
//
//			this.variable.subscript = new TokenList<Rust_Subscript>();
//			this.variable.subscript.addToken(subscript);
//		}

		return Rust_Generator.wrapExpression(this);
	}
}
