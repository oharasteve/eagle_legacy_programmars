// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Terminals;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.programmar.Powershell.Powershell_Format;
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
		super("\"'", true, '`', true, false);	// Careful with the funny backtick
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String result = Powershell_Format.format(interpreter, removeQuotes());
		interpreter.pushStr(result);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		return Powershell_Format.compile(generator, removeQuotes(), this);
	}
}
