// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_Literal extends TerminalLiteralToken
		implements EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		if (rec.charAt(_currentChar) == '@')
		{
			lines.setCurrentChar(_currentChar + 1);
			if (genericLiteral(lines, "\"", false, '?', true, true)) return true;
			lines.setCurrentChar(_currentChar);
			return false;
		}

		// An "interpolated" string can have {{ and }} in it
		if (rec.charAt(_currentChar) == '$')
		{
			lines.setCurrentChar(_currentChar + 1);
			if (genericLiteral(lines, "\"", true, '\\', false, false)) return true;
			lines.setCurrentChar(_currentChar);
			return false;
		}

		return genericLiteral(lines, "\"", true, '\\', false, false);
	}

	@Override
	public String description()
	{
		return "C# literals may have @ or $";
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newLiteralExpression(_txt.replaceAll("\"", ""), this);
	}

	public static CSharp_Literal generateLiteral(String value, AbstractToken source)
	{
		CSharp_Literal lit = new CSharp_Literal();
		String val = value;
		if (!val.startsWith("\""))
		{
			val = '"' + val + '"';
		}
		lit.setValue(val);
		lit.setTransformationSource(source);
		return lit;
	}

	public static CSharp_Expression generateLiteralExpression(String value, AbstractToken source)
	{
		CSharp_Literal lit = CSharp_Literal.generateLiteral(value, source);
		return CSharp_Generator.wrapExpression(lit);
	}
}
