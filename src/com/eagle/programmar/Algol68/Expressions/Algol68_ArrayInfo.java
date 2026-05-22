// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.programmar.Algol68.Symbols.Algol68_Identifier_Reference;
import com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Algol68_ArrayInfo extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Algol68_KeywordChoice UPB = new Algol68_KeywordChoice("UPB", "LWB");
	public @S(20) Algol68_Variable arrayName;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Algol68_Identifier_Reference id = arrayName.vars.first();
		EagleValue val = interpreter.findSymbol(id.getValue());

		if (val instanceof EagleString)
		{
			EagleString str = (EagleString) val;
			switch (UPB.getValue())
			{
			case "LWB":
				interpreter.pushInt(1);
				return;
			case "UPB":
				interpreter.pushInt(str.forceStringValue().length());
				return;
			}
		}

		throw new RuntimeException("Unable to handle " + UPB.getValue());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		switch (UPB.toString().toUpperCase())
		{
		case "UPB":
			AbstractExpression varExpr = generator.newVariableExpression(
					arrayName.vars.first().getValue(), SubscriptEnum.FIRST_IS_ZERO, null, UPB);
			return generator.newLengthFunction(varExpr, this);
		case "LWB":
			return generator.newNumberExpression("1", this);
		default:
			throw new RuntimeException("Unexpected string operator: " + UPB);
		}
	}
}
