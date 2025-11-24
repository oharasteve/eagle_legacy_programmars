// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Variable;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.SubscriptEnum;

public class TCL_BracketLindex extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) TCL_Keyword LINDEX = new TCL_Keyword("lindex");
	public @S(30) TCL_Variable arrayVar;
	public @S(40) TCL_Expression index;
	public @S(50) PunctuationRightBracket rightBracket;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(arrayVar);
		EagleArray array = (EagleArray) value;
		int i = interpreter.getIntValue(index);
		interpreter.pushEagleValue(array.getValue(i));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression indexExpr = transformer.transformExpression(generator, index);
		return generator.newVariableExpression(arrayVar.id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, indexExpr, this);
	}
}
