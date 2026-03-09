// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 6, 2015

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class CSharp_Character_Literal extends TerminalLiteralToken
{
	public CSharp_Character_Literal()
	{
		super("'", true, '\\', false, false);
	}

	public CSharp_Character_Literal generateCharLiteral(String value, AbstractToken source)
	{
		this.setValue(value);
		this.setTransformationSource(source);
		return this;
	}
}