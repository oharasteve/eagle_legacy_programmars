// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2026

package com.eagle.programmar.Eaglish.Data;

import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.StaticEnum;
import com.eagle.transform.EagleTransformer;

public abstract class Eaglish_Data extends TokenSequence
{
	public static class Eaglish_InitialValue extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Eaglish_Expression expression;
	}

	protected AbstractStatement transformData(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			String name, AbstractType newType, AbstractExpression initial)
	{
		int asgs = transformer._metrics.countAssignments(name, null);
		StaticEnum isConst = StaticEnum.NONE;
		if (asgs == 1) isConst = StaticEnum.CONST;			
		return generator.newDataDeclaration(isConst, name, null, newType, initial, this);
	}
}
