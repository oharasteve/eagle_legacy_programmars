// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 6, 2015

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.generate.Terminals.Eagle_Generate_CharLiteral;
import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class CSharp_Character_Literal extends TerminalLiteralToken
		implements Eagle_Generate_CharLiteral
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "'", true, '\\', false, false);
	}
	
	@Override
	public String description()
	{
		return super.genericDescription("'", true, '\\', false, false);
	}

	@Override
	public CSharp_Character_Literal generateCharLiteral(String value, AbstractToken source)
	{
		this.setValue(value);
		this.setTransformationSource(source);
		return this;
	}
}