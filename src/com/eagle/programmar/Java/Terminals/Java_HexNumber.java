// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Java.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class Java_HexNumber extends TerminalHexNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericHex(lines, "0x", "Ll", true);
	}

	public static Java_HexNumber generateHexNumber(String value, AbstractToken source)
	{
		Java_HexNumber hex = new Java_HexNumber();
		hex.setValue(value);
		hex.setTransformationSource(source);
		return hex;
	}
}
