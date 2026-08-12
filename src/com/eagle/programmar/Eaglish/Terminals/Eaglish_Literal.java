// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Terminals;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Eaglish_Literal extends TerminalLiteralToken
		implements EagleTransformableExpression
{
	public Eaglish_Literal()
	{
		super("\"", true, '\\', false, false);
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String result = TerminalLiteralExpression.format(interpreter,
				Eaglish_Expression.class, removeQuotes(), '\\', '^', '^');
		interpreter.pushStr(result);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression result = TerminalLiteralExpression.compile(transformer, generator,
				Eaglish_Expression.class, removeQuotes(), '\\', '^', '^', this);
		return result;
	}
}
