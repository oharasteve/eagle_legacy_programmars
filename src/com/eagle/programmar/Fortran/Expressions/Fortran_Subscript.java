// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Symbols.Fortran_Identifier_Reference;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Fortran_Subscript extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Fortran_Identifier_Reference variable;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Fortran_Expression, PunctuationColon> args;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(variable.toString());
		if (value.isString() && args.getPrimaryCount() == 2)
		{
			String str = value.forceStringValue();
			int len = str.length();
			int sc = interpreter.getIntValue(args.getPrimaryElement(0));
			int ec = interpreter.getIntValue(args.getPrimaryElement(1));
			if (ec > len) ec = len;
			String substr = str.substring(sc - 1, ec);
			interpreter.pushStr(substr);
		}
		else
		{
			throw new RuntimeException("Unable to handle subscript on " + variable.toString());
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (args.getPrimaryCount() != 2)
		{
			return null;
		}
		
		AbstractExpression theExpr = generator.newVariableExpression(variable.toString(), null, null, this);
		AbstractExpression sc = transformer.transformExpression(generator, args.getPrimaryElement(0));
		AbstractExpression ec = transformer.transformExpression(generator, args.getPrimaryElement(1));
		return generator.newSubstringFunction(theExpr, sc, SubstringSCEnum.FIRST_CHAR_IS_ONE,
				SubstringECEnum.GIVEN_EC, ec, true, this);
	}
}
