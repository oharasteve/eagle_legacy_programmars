// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Terminals;

import com.eagle.interpret.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalNumberToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Eaglish_Number extends TerminalNumberToken
		implements EagleRunnable, EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, null, null, true, false, '?');
	}

	@Override
	public String description()
	{
		return super.genericDescription(null, null, true, false, '?');
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newNumberExpression(_numberAsText, this);
	}
}
