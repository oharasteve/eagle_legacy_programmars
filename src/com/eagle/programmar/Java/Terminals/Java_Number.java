// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java.Terminals;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalNumberToken;

public class Java_Number extends TerminalNumberToken
{
	public Java_Number()
	{
		super("Ee", "LlFfDd", true, true, '_');
	}

	public static Java_Number createNumber(int value)
	{
		return generateNumber(Integer.toString(value), null);
	}
	
	public static Java_Number generateNumber(String value, AbstractToken source)
	{
		Java_Number num = new Java_Number();
		num.setValue(value);
		num.setTransformationSource(source);
		return num;
	}
	
	public static Java_Expression generateNumberExpression(String value, AbstractToken source)
	{
		return Java_Generator.wrapExpression(generateNumber(value, source));
	}
}
