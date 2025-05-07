// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java.Terminals;

import com.eagle.generate.Terminals.Eagle_Generate_Number;
import com.eagle.interpret.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalNumberToken;

public class Java_Number extends TerminalNumberToken
		implements EagleRunnable, Eagle_Generate_Number
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, "Ee", "LlFfDd", true, true, '_');
	}
	
	@Override
	public String description()
	{
		return super.genericDescription("Ee", "LlFfDd", true, true, '_');
	}
	
	@Override
	public Java_Number generateNumber(String value, AbstractToken source)
	{
		this.setValue(value);
		this.setTransformationSource(source);
		return this;
	}
}
