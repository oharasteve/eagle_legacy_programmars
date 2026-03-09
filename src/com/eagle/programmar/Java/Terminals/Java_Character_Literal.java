// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 6, 2015

package com.eagle.programmar.Java.Terminals;

import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Java_Character_Literal extends TerminalLiteralToken
{
	public Java_Character_Literal()
	{
		super("'", true, '\\', false, false);
	}

	public static Java_Character_Literal generateCharLiteral(String value, AbstractToken source)
	{
		Java_Character_Literal lit = new Java_Character_Literal();
		lit.setValue(value);
		lit.setTransformationSource(source);
		return lit;
	}
}