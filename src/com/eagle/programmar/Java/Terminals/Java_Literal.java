// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_Literal extends TerminalLiteralToken
		implements EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "\"", true, '\\', false, false);
	}

	@Override
	public String description()
	{
		return super.genericDescription("\"", true, '\\', false, false);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newLiteralExpression(_txt.replaceAll("\"", ""), this);
	}

	public Java_Literal generateLiteral(String value, AbstractToken source)
	{
		String val = value;
		if (!val.startsWith("\""))
		{
			val = '"' + val + '"';
		}
		this.setValue(val);
		this.setTransformationSource(source);
		return this;
	}

	public static Java_Expression generateLiteralExpression(String value, AbstractToken source)
	{
		Java_Literal lit = new Java_Literal();
		return Java_Generator.wrapExpression(lit.generateLiteral(value, source));
	}
}
