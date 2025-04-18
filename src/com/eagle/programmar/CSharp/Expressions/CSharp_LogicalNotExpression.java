// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.Expressions.Eagle_Generate_Logical_Not;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_LogicalNotExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression,
				Eagle_Generate_Logical_Not<CSharp_Expression>
{
	public @S(10) CSharp_Punctuation notOperator = new CSharp_Punctuation('!');
	public @S(20) CSharp_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean value = interpreter.getBoolValue(expr);
		interpreter.pushBool(!value);
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		return generator.newNotExpression(theExpr, this);
	}
	
	@Override
	public CSharp_Expression generateLogicalNot(CSharp_Expression theExpr,
			AbstractToken source)
	{
		this.expr = (CSharp_Expression) theExpr;
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
