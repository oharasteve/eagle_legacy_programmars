// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 15, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.generate.AssignmentEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Variable;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class VB_AssignmentStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) VB_Variable var;
	public @S(20) PunctuationEquals equals;
	public @S(30) VB_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		interpreter.setSymbol(var.var, var.var.getValue(), value);

		// VB doesn't have a Return statement. It assigns a value to the function name
		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (parent instanceof VB_Function)
			{
				VB_Function func = (VB_Function) parent;
				if (var.var.getValue().equals(func.id.getValue()))
				{
					func._returnMetrics.returned(value.getType());
				}
				break;
			}
			parent = parent.getParent();
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// VB doesn't have a Return statement. It assigns a value to the function name
		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (parent instanceof VB_Function)
			{
				VB_Function func = (VB_Function) parent;
				if (var.var.getValue().equals(func.id.getValue()))
				{
					AbstractExpression retExpr = transformer.transformExpression(generator, expr);
					return generator.newReturnStatement(retExpr, this);
				}
				break;
			}
			parent = parent.getParent();
		}

		// Normal assignment ...
		AbstractExpression subscrExpr = null;
		if (var.subscript != null && var.subscript.isPresent())
		{
			subscrExpr = transformer.transformExpression(generator, var.subscript.exprs.first());
		}
		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractExpression asgExpr = generator.newAssignmentExpression(var.var.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, AssignmentEnum.EQUALS, value, this);
		AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
		return exprStmt;
	}
}
