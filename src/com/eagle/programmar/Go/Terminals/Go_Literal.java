// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Go_Literal extends TerminalLiteralToken
		implements EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();

		if (_currentChar < nc)
		{
			if (rec.charAt(_currentChar) == '`')
			{
				// Allow multiline for back tick (`)
				return genericLiteral(lines, "`", true, '\\', false, true);
			}
		}

		return genericLiteral(lines, "\"", true, '\\', false, false);
	}

	@Override
	public String description()
	{
		return "go literal depends on back tick (`)";
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
}
