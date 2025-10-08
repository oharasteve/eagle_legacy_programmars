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

		return genericLiteral(lines, "\"'`", true, '\\', false, true);
	}
	
	@Override
	public String description()
	{
		return "perl literal depends on << and <<<";
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newLiteralExpression(_txt.replaceAll("\"", ""), this);
	}
}
