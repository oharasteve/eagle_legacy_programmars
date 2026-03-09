// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Terminals;

import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class SQL_Literal extends TerminalLiteralToken
		implements EagleTransformableExpression
{
	public SQL_Literal()
	{
		super("'`\"", true, '\\', true, false);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		String text = this.removeQuotes().replaceAll("\\\\n", "XX\nXX");
		return generator.newLiteralExpression(text, this);
	}
}
