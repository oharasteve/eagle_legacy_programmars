// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Python.Terminals;

import com.eagle.generate.Terminals.Eagle_Generate_Number;
import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalNumberToken;

public class Python_Number extends TerminalNumberToken
		implements Eagle_Generate_Number
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		// J is for complex ...
		return genericNumber(lines, "Ee", "JjLl", true, false, '?');
	}
	
	@Override
	public String description()
	{
		return super.genericDescription("Ee", "JjLl", true, false, '?');
	}

	@Override
	public Python_Number generateNumber(String value, AbstractToken source)
	{
		this.setValue(value);
		this.setTransformationSource(source);
		return this;
	}
	
	public static Python_Number createNumber(int value)
	{
		Python_Number num = new Python_Number();
		num.setValue(Integer.toString(value));
		return num;
	}
}
