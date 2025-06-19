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
import com.eagle.transform.EagleGenerator.IncrementEnum;

public class CSharp_PreIncrementExpression extends PrimaryOperator
		implements EagleRunnable
{
	public @S(10) @NOSPACE CSharp_PunctuationChoice operator =
			new CSharp_PunctuationChoice("++", "--");
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
	
	public CSharp_Expression generateIncrement(CSharp_Variable variable,
			IncrementEnum oper, AbstractToken source)
	{
		this.var = variable;
		switch (oper)
		{
		case INCREMENT:
			this.operator.setValue("++");
			break;
		case DECREMENT:
			this.operator.setValue("--");
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + oper);
		}
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
