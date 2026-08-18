// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 18, 2026

package com.eagle.programmar.SQL.Terminals;

import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class SQL_HexNumber extends TerminalHexNumberToken
{
	public SQL_HexNumber()
	{
		super("0x", "", true);
	}
	
	public static SQL_HexNumber generateHexNumber(String value, AbstractToken source)
	{
		SQL_HexNumber hex = new SQL_HexNumber();
		hex.setValue(value);
		hex.setTransformationSource(source);
		return hex;
	}
}
