// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalNumberToken;

public class CSharp_Number extends TerminalNumberToken
{
	public CSharp_Number()
	{
		super("Ee", "LlFfDdUuMm", true, false, '?');
	}

	public static CSharp_Number createNumber(int value)
	{
		return generateNumber(Integer.toString(value), null);
	}
	
	public static CSharp_Number generateNumber(String value, AbstractToken source)
	{
		CSharp_Number num = new CSharp_Number();
		num.setValue(value);
		num.setTransformationSource(source);
		return num;
	}

	public static CSharp_Expression generateNumberExpression(String value, AbstractToken source)
	{
		return CSharp_Generator.wrapExpression(generateNumber(value, source));
	}
}
