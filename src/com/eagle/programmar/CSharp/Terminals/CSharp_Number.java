// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalNumberToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_Number extends TerminalNumberToken
		implements EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, "Ee", "LlFfDdUuMm", true, false, '?');
	}

	@Override
	public String description()
	{
		return super.genericDescription("Ee", "LlFfDdUuMm", true, false, '?');
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
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newNumberExpression(_numberAsText, this);
	}
}
