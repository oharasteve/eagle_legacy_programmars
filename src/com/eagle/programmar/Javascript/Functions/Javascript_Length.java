// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Javascript_Length extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Javascript_Variable variableName;
	public @S(20) PunctuationPeriod dot;
	public @S(30) Javascript_Keyword LENGTH = new Javascript_Keyword("length");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = variableName.firstId.getWhich();
		Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) which;
		EagleValue val = interpreter.findSymbol(id.getValue());
		String str = val.forceStringValue();
		interpreter.pushInt(str.length());
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractToken which = variableName.firstId.getWhich();
		if (! (which instanceof Javascript_Identifier_Reference))
		{
			throw new RuntimeException("Unable to handle " + which);
		}
		Javascript_Identifier_Reference idRef = (Javascript_Identifier_Reference) which;
		AbstractExpression theExpr = generator.newVariableExpression(idRef.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, null, this);
		return generator.newLengthFunction(theExpr, this);
	}
}
