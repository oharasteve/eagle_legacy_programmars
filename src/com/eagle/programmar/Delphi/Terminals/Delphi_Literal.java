// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.Delphi.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Delphi_Literal extends TerminalLiteralToken
		implements EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "'", false, '?', true, false);
	}

	@Override
	public String description()
	{
		return super.genericDescription("'", false, '?', true, false);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		String txt = _txt;
		if (txt.startsWith("'") && txt.endsWith("'"))
		{
			txt = txt.substring(1, txt.length() - 1);
		}
		return generator.newLiteralExpression(txt, this);
	}
}
