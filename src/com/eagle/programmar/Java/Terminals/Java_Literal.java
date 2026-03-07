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
		String val = _txt;
		int nc = val.length();
		if (val.startsWith("\"") && val.endsWith("\"") && nc > 1)
		{
			val = val.substring(1, nc-1).replaceAll("\\\\\"", "\"");
		}
		return generator.newLiteralExpression(val, this);
	}

	public static Java_Literal generateLiteral(String value, AbstractToken source)
	{
		Java_Literal lit = new Java_Literal();
		String val = '"' + value
				.replaceAll("\\\\", "\\\\\\\\")
				.replaceAll("\"", "\\\\\"")
				.replaceAll("\n", "\\n")
				+ '"';
		lit.setValue(val);
		lit.setTransformationSource(source);
		return lit;
	}

	public static Java_Expression generateLiteralExpression(String value, AbstractToken source)
	{
		return Java_Generator.wrapExpression(generateLiteral(value, source));
	}
}
