// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Go_ReturnStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("#Return_statements") Go_Keyword RETURN = new Go_Keyword("return");
	public @S(20) Go_Expression expression;
	public @S(30) Go_EOLN eoln;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (expression != null && expression.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(expression);
			interpreter.pushEagleValue(val);

			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (parent instanceof Go_Function)
				{
					Go_Function func = (Go_Function) parent;
					func._returnMetrics.returned(val.typeName());
					break;
				}
				parent = parent.getParent();
			}
		}
		return Eagle_Statement_Result.RETURN;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression retExpr = null;
		if (expression != null && expression.isPresent())
		{
			retExpr = transformer.transformExpression(generator, expression);
		}
		return generator.newReturnStatement(retExpr, this);
	}
}
