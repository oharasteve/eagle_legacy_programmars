// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Python.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalNumberToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Number extends TerminalNumberToken
		implements EagleTransformableExpression
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

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newNumberExpression(_numberAsText, this);
	}
}
