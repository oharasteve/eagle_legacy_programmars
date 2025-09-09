// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalNumberToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Go_Number extends TerminalNumberToken
		implements EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, "Ee", "LlFfDd", true, false, '?');
	}
	
	@Override
	public String description()
	{
		return super.genericDescription("Ee", "LlFfDd", true, false, '?');
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newNumberExpression(_numberAsText, this);
	}
}
