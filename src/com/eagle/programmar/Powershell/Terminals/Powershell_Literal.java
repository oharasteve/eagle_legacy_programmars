// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Terminals;

import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
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
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		String str = removeQuotes().replaceAll("`", "");
		return generator.newLiteralExpression(str, this);
	}
}
