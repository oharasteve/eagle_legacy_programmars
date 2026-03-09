// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java.Terminals;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Java_Literal extends TerminalLiteralToken
{
	public Java_Literal()
	{
		super("\"", true, '\\', false, false);
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
