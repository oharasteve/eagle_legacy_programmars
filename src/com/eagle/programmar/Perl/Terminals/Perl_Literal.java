// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Perl_Literal extends TerminalLiteralToken
		implements EagleTransformableExpression
{
	public Perl_Literal()
	{
		super("\"'`", true, '\\', false, true);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		// Check for <<<STOPPER lines STOPPER
		EagleLineReader rec = lines.get(_currentLine);
		if (multilineStopper(lines, rec, "<<<")) return true;
		if (multilineStopper(lines, rec, "<<")) return true;

		return super.parse(lines);
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String text = this.removeQuotes()
				.replaceAll("\\\\n", "")
				.replaceAll("\\\\\\$", "\\$");
		return generator.newLiteralExpression(text, this);
	}
}
