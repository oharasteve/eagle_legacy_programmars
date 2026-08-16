// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Terminals;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Powershell_Literal extends TerminalLiteralToken
		implements EagleTransformableExpression
{
	public Powershell_Literal()
	{
		super("\"'", true, '`', true, false);	// Careful with the funny back-tick
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		ArgumentsMetrics metrics = null;
		String result = Powershell_LiteralExpression.interpret(interpreter, removeQuotes(), metrics);
		interpreter.pushStr(result);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<TypeEnum> metrics = null;
		return Powershell_LiteralExpression.transform(transformer, generator, this, metrics, this);
	}
}
