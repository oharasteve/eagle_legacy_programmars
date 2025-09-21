// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Julia_ReturnStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
				EagleTransformableStatement
{
	public @S(10) @DOC("manual/functions/#The-return-Keyword") Julia_Keyword RETURN = new Julia_Keyword("return");
	public @S(20) Julia_Expression expression;
	public @S(30) Julia_EOLN eoln;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expression);
		
		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (parent instanceof Julia_Function)
			{
				Julia_Function func = (Julia_Function) parent;
				func._returnMetrics.returned(val.typeName());
				break;
			}
			parent = parent.getParent();
		}

		interpreter.pushEagleValue(val);
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
