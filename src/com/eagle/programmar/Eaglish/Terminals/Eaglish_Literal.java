// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Terminals;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.Eaglish.Eaglish_Format;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Eaglish_Literal extends TerminalLiteralToken
		implements EagleRunnable, EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "\"", true, '\\', false, false);
	}

	@Override
	public String description()
	{
		return super.genericDescription("\"", true, '\\', false, false);
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String result = Eaglish_Format.format(interpreter, _txt);
		interpreter.pushStr(result);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		if (_txt.indexOf('^') >= 0)
		{
			return Eaglish_Format.compile(transformer, generator, _txt, this);
		}
		return generator.newLiteralExpression(_txt.replaceAll("\"", ""), this);
	}
}
