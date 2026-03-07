// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Perl_Literal extends TerminalLiteralToken
		implements EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		// Check for <<<STOPPER lines STOPPER
		EagleLineReader rec = lines.get(_currentLine);
		if (multilineStopper(lines, rec, "<<<")) return true;
		if (multilineStopper(lines, rec, "<<")) return true;

		return super.genericLiteral(lines, "\"'`", true, '\\', false, true);
	}

	@Override
	public String description()
	{
		return super.genericDescription("\"'`", true, '\\', false, true);
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
