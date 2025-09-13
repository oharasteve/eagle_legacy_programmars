// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_Format;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Algol68_PrintfStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Algol68_Keyword PRINTF = new Algol68_Keyword("PRINTF");
	public @S(20) Algol68_Punctuation doubleLeftParen = new Algol68_Punctuation("((");
	public @S(30) Algol68_Format format;
	public @S(40) PunctuationComma comma;
	public @S(50) Algol68_Expression expr;
	public @S(60) Algol68_Punctuation doubleRightParen = new Algol68_Punctuation("))");
	public @S(70) @OPT PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fmt = interpreter.getStrValue(format);
		Integer val = Integer.valueOf(interpreter.getIntValue(expr));
		switch (fmt)
		{
		case "$d$":
			System.out.format("%1d", val);
			break;
		case "$dd$":
			System.out.format("%2d", val);
			break;
		case "$ddd$":
			System.out.format("%3d", val);
			break;
		case "$dddd$":
			System.out.format("%4d", val);
			break;
		default:
			throw new RuntimeException("Unable to printf " + val + " using " + fmt);
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		String fmt;							// ??????????????????? TBD ??????????????????????
		AbstractExpression line = null;		// ??????????????????? TBD ??????????????????????
		return generator.newPrintStatement(line, false, this);
	}
}
