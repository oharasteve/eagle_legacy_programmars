// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Javascript_DocumentWriteln extends PrimaryOperator
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) Javascript_Keyword DOCUMENT = new Javascript_Keyword("document");
	public @S(20) PunctuationPeriod dot;
	public @S(30) Javascript_KeywordChoice WRITELN = new Javascript_KeywordChoice(
			"write", "writeln");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Javascript_Expression expr;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean newLine;
		switch (WRITELN.getValue())
		{
		case "write":
			newLine = false;
			break;
		case "writeln":
			newLine = true;
			break;
		default:
			throw new RuntimeException("Unexpected WRITELN value: " + WRITELN.getValue());
		}

		String val = interpreter.getStrValue(expr);
		if (val.startsWith("<br>")) val = val.substring(4);	// Toss leading <br> if present
		if (newLine)
		{
			System.out.println(val);
		}
		else
		{
			System.out.print(val);
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		boolean newLine;
		switch (WRITELN.getValue())
		{
		case "write":
			newLine = false;
			break;
		case "writeln":
			newLine = true;
			break;
		default:
			throw new RuntimeException("Unexpected WRITE value: " + WRITELN.getValue());
		}
		
		AbstractExpression value = transformer.transformExpression(generator, expr);
		return generator.newPrintStatement(value, newLine, false, this);
	}
}
