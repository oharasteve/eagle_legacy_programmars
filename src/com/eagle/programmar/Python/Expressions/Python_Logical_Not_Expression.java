// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Logical_Not_Expression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Keyword NOT = new Python_Keyword("not");
	public @S(20) Python_Expression expr;

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
	
	public Python_Expression generateLogicalNot(Python_Expression theExpr,
			AbstractToken source)
	{
		this.expr = theExpr;
		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}
