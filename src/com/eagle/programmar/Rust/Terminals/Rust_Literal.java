// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

package com.eagle.programmar.Rust.Terminals;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_Literal extends TerminalLiteralToken
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
		interpreter.pushStr(_txt);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newLiteralExpression(_txt.replaceAll("\"", ""), this);
	}
}
