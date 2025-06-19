// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalNumberToken;

public class CSharp_Number extends TerminalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, "Ee", "LlFfDdUuMm", true, false, '?');
	}
	
	@Override
	public String description()
	{
		return super.genericDescription("Ee", "LlFfDdUuMm", true, false, '?');
	}
	
	public CSharp_Number generateNumber(String value, AbstractToken source)
	{
		this.setValue(value);
		this.setTransformationSource(source);
		return this;
	}
}
