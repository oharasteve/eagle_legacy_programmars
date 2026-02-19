// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2014

package com.eagle.programmar.Python.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class Python_HexNumber extends TerminalHexNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericHex(lines, "0x", "Ll");
	}

	public static Python_HexNumber generateHexNumber(String value, AbstractToken source)
	{
		Python_HexNumber hex = new Python_HexNumber();
		hex.setValue(value);
		hex.setTransformationSource(source);
		return hex;
	}
}
