// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class COBOL_Literal extends TerminalLiteralToken implements EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "'\"", false, '?', true, false);
	}
	
	@Override
	public String description()
	{
		return super.genericDescription("'\"", false, '?', true, false);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newLiteralExpression(_txt.replaceAll("[\"']", ""), this);
	}
}
