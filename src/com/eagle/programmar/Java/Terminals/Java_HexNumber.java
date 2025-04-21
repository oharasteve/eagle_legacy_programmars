// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Java.Terminals;

import com.eagle.generate.Terminals.Eagle_Generate_HexNumber;
import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class Java_HexNumber extends TerminalHexNumberToken
		implements Eagle_Generate_HexNumber
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericHex(lines, "0x", "Ll", true);
	}
	
	@Override
	public Java_HexNumber generateHexNumber(String value, AbstractToken source)
	{
		this.setValue(value);
		this.setTransformationSource(source);
		return this;
	}
}
