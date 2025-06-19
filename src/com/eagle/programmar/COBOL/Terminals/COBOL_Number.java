// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 7, 2010

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalNumberToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class COBOL_Number extends TerminalNumberToken implements EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, null, null, false, false, '?');
	}
	
	@Override
	public String description()
	{
		return super.genericDescription(null, null, false, false, '?');
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newNumberExpression(_numberAsText, this);
	}
}
