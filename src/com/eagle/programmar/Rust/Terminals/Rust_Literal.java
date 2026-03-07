// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

package com.eagle.programmar.Rust.Terminals;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_Literal extends TerminalLiteralToken
		implements EagleRunnable, EagleTransformableExpression
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
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.pushStr(_txt);
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

	public static Rust_Literal generateLiteral(String value, AbstractToken source)
	{
		Rust_Literal lit = new Rust_Literal();
		String val = '"' + value
				.replaceAll("\\\"", "\\\\\"")
				.replaceAll("\n", "\\n")+ '"';
		lit.setValue(val);
		lit.setTransformationSource(source);
		return lit;
	}

	public static Rust_Expression generateLiteralExpression(String value, AbstractToken source)
	{
		return Rust_Generator.wrapExpression(generateLiteral(value, source));
	}
}
