// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Terminals;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.TCL.TCL_Format;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class TCL_Literal extends TerminalLiteralToken
		implements EagleRunnable, EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "\"", false, '?', false, false);
	}
	
	@Override
	public String description()
	{
		return super.genericDescription("\"", false, '?', false, false);
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String result = TCL_Format.format(interpreter, _txt);
		interpreter.pushStr(result);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return TCL_Format.compile(generator, _txt, this);
	}
}
