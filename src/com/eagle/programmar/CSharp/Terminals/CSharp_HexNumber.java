// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class CSharp_HexNumber extends TerminalHexNumberToken
{
	public CSharp_HexNumber()
	{
		super("0x", "LlUu", false);
	}

	public static CSharp_HexNumber generateHexNumber(String value, AbstractToken source)
	{
		CSharp_HexNumber hex = new CSharp_HexNumber();
		hex.setValue(value);
		hex.setTransformationSource(source);
		return hex;
	}
}
