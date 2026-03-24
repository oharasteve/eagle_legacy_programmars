// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.IncrementEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_PreIncrementExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) @NOSPACE CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("++", "--");
	public @S(20) @NOSPACE CSharp_Variable var;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (var.firstId.getWhich() instanceof CSharp_Identifier_Reference)
		{
			CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) var.firstId.getWhich();
			EagleValue val = interpreter.findSymbol(id.getValue());
			int prev = val.forceIntegerValue();
			int curr;
			switch (operator.getValue())
			{
			case "++":
				curr = prev + 1;
				break;
			case "--":
				curr = prev - 1;
				break;
			default:
				throw new RuntimeException("Unexpected operator: " + operator);
			}
			interpreter.setSymbol(var, id.getValue(), new EagleInteger(curr));
			interpreter.pushInt(curr);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		IncrementEnum whichDirection;
		switch (operator.getValue())
		{
		case "++":
			whichDirection = IncrementEnum.INCREMENT;
			break;
		case "--":
			whichDirection = IncrementEnum.DECREMENT;
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + operator);
		}
		CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) var.firstId.getWhich();
		return generator.newPostIncrementExpression(id.getValue(), SubscriptEnum.FIRST_IS_ZERO, null, whichDirection,
				this);
	}

	public static CSharp_Expression generateIncrement(CSharp_Variable variable,
			IncrementEnum oper, AbstractToken source)
	{
		CSharp_PreIncrementExpression preExpr = new CSharp_PreIncrementExpression();
		preExpr.var = variable;
		switch (oper)
		{
		case INCREMENT:
			preExpr.operator.setValue("++");
			break;
		case DECREMENT:
			preExpr.operator.setValue("--");
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + oper);
		}
		preExpr.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(preExpr);
	}
}
