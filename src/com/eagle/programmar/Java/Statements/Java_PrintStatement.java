// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2024

package com.eagle.programmar.Java.Statements;

import java.io.PrintStream;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Java_PrintStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable, EagleTransformableStatement
{
	public @S(10) @NEWLINE Java_Keyword SYSTEM = new Java_Keyword("System");
	public @S(20) @NOSPACE PunctuationPeriod dot1;
	public @S(30) @NOSPACE Java_KeywordChoice OUT = new Java_KeywordChoice("err", "out");
	public @S(40) @NOSPACE PunctuationPeriod dot2;
	public @S(50) @NOSPACE Java_KeywordChoice PRINT = new Java_KeywordChoice("print", "println");
	public @S(60) @NOSPACE PunctuationLeftParen leftParen;
	public @S(70) @NOSPACE @OPT Java_Expression expr;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;
	public @S(90) @NOSPACE PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = interpreter.getStrValue(expr);
		PrintStream prt = System.out;
		if (OUT.getValue().equals("err")) prt = System.err;
		switch (PRINT.getValue())
		{
		case "print":
			prt.print(val);
			return;
		case "println":
			prt.println(val);
			return;
		}
		
		throw new RuntimeException("Unexpected keyword: " + PRINT.getValue());
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		boolean newLine;
		switch (PRINT.getValue())
		{
		case "print":
			newLine = false;
			break;
		case "println":
			newLine = true;
			break;
		default:
			throw new RuntimeException("Unexpected PRINT value: " + PRINT.getValue());
		}
		
		AbstractExpression value = transformer.transformExpression(generator, expr);
		return generator.newPrintStatement(value, newLine, this);
	}

	public Java_Statement generatePrintStmt(Java_Expression line, boolean newLine,
			AbstractToken source)
	{
		Java_PrintStatement prt = new Java_PrintStatement();
		prt.dot1 = new PunctuationPeriod();
		prt.dot1.setPresent(true);
		prt.OUT = new Java_KeywordChoice("out");
		prt.dot2 = new PunctuationPeriod();
		prt.dot2.setPresent(true);
		
		if (newLine)
		{
			prt.PRINT = new Java_KeywordChoice("println");
		}
		else
		{
			prt.PRINT = new Java_KeywordChoice("print");
		}
		
		prt.leftParen = new PunctuationLeftParen();
		prt.rightParen = new PunctuationRightParen();

		prt.expr = line;
		if (line != null)
		{
			prt.expr.setPresent(true);
		}
		prt.semicolon = new PunctuationSemicolon();
		
		prt.setTransformationSource(source);
		return Java_Generator.wrapStatement(prt);
	}
}
