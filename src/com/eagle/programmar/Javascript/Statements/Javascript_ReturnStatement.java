// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Function;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Javascript_ReturnStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("js_functions.asp") Javascript_Keyword RETURN = new Javascript_Keyword("return");
	public @S(20) @OPT Javascript_Expression expression;
	public @S(30) @OPT PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expression);
		
		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (parent instanceof Javascript_Function)
			{
				Javascript_Function func = (Javascript_Function) parent;
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
		AbstractExpression expr = transformer.transformExpression(generator, expression);
		return generator.newReturnStatement(expr, this);
	}
}
