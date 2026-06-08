// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Python.Terminals;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalNumberToken;

public class Python_Number extends TerminalNumberToken
{
	public Python_Number()
	{
		// J is for complex ...
		// 4_000 is the same as 4,000
		super("Ee", "JjLl", true, true, '_');
	}
	
	public static Python_Number createNumber(int value)
	{
		return generateNumber(Integer.toString(value), null);
	}

	public static Python_Number generateNumber(String value, AbstractToken source)
	{
		Python_Number num = new Python_Number();
		num.setValue(value);
		num.setTransformationSource(source);
		return num;
	}

	public static Python_Expression generateNumberExpression(String value, AbstractToken source)
	{
		Python_Number num = generateNumber(value, source);
		return Python_Generator.wrapExpression(num);
	}
}
