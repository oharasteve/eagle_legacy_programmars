// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Terminals;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class AWK_Literal extends TerminalLiteralToken
		implements EagleRunnable, EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "'\"", true, '\\', false, false);
	}

	@Override
	public String description()
	{
		return super.genericDescription("'\"", true, '\\', false, false);
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = _txt;
		if (str.startsWith("\"") || str.startsWith("'"))
		{
			str = str.substring(1, str.length() - 1); // Remove quotes
		}
		interpreter.pushStr(str);
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		String val = _txt;
		int nc = val.length();
		if (val.startsWith("\"") && val.endsWith("\"") && nc > 1)
		{
			val = val.substring(1, nc-1).replaceAll("\\\\\"", "\"");
		}
		return generator.newLiteralExpression(val, this);
	}
}
