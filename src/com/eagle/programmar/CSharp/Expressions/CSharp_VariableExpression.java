// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.generate.EagleGenerator.AdditiveEnum;
import com.eagle.generate.EagleGenerator.SubscriptEnum;
import com.eagle.generate.Expressions.Eagle_Generate_VarExpr;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Subscript;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class CSharp_VariableExpression extends PrimaryOperator
		implements EagleRunnable, Eagle_Generate_VarExpr<CSharp_Expression>
{
	public @S(10) CSharp_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}
	
	@Override
	public CSharp_Expression generateVarExpr(String name, SubscriptEnum offset,
			CSharp_Expression subscrExpr, AbstractToken source)
	{
		this.variable = CSharp_Variable.newVariable(name);

		if (subscrExpr != null)
		{
			CSharp_Subscript subscript = new CSharp_Subscript();
			subscript.leftBracket = new PunctuationLeftBracket();
			
			if (offset == SubscriptEnum.FIRST_IS_ONE)
			{
				CSharp_Number num = new CSharp_Number();
				CSharp_Expression one = CSharp_Generator.wrapExpression(num.generateNumber("1", source));
				CSharp_AdditiveExpression addExp = new CSharp_AdditiveExpression();
				Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
				CSharp_Expression minusOne = addExp.generateAdditive(types, subscrExpr,
						AdditiveEnum.MINUS, one, source);
				subscript.expr = minusOne;
			}
			else
			{
				subscript.expr = subscrExpr;
			}
			subscript.expr.setPresent(true);
			subscript.rightBracket = new PunctuationRightBracket();

			this.variable.subscript = new TokenList<CSharp_Subscript>();
			this.variable.subscript.addToken(subscript);
		}

		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
