// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Function;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class C_ReturnStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement,
		EagleTransformableStatement
{
	public @S(10) @DOC("#The-return-Statement") C_Keyword RETURN = new C_Keyword("return");
	public @S(20) @OPT C_Expression expression;
	public @S(30) PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (expression != null && expression.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(expression);

			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (parent instanceof C_Function)
				{
					C_Function func = (C_Function) parent;
					func._returnMetrics.returned(val.typeName());
					break;
				}
				parent = parent.getParent();
			}

			interpreter.pushEagleValue(val);
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
