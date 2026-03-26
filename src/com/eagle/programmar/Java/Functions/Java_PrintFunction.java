// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2024

package com.eagle.programmar.Java.Functions;

import java.io.PrintStream;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_PrintFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) @NEWLINE Java_Keyword SYSTEM = new Java_Keyword("System");
	public @S(20) @NOSPACE PunctuationPeriod dot1;
	public @S(30) @NOSPACE Java_KeywordChoice OUT = new Java_KeywordChoice("err", "out");
	public @S(40) @NOSPACE PunctuationPeriod dot2;
	public @S(50) @NOSPACE Java_KeywordChoice PRINT = new Java_KeywordChoice("print", "println");
	public @S(60) @NOSPACE PunctuationLeftParen leftParen;
	public @S(70) @NOSPACE @OPT Java_Expression expr;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;

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
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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
		return generator.newPrintFunction(value, TypeEnum.STRING, newLine, false, this);
	}

	public static Java_Expression generatePrintFunc(Java_Expression line, TypeEnum type,
			boolean newLine, boolean toErr, AbstractToken source)
	{
		Java_PrintFunction prtFn = new Java_PrintFunction();
		prtFn.dot1 = new PunctuationPeriod();
		prtFn.dot1.setPresent(true);
		if (toErr)
		{
			prtFn.OUT = new Java_KeywordChoice("err");
		}
		else
		{
			prtFn.OUT = new Java_KeywordChoice("out");
		}
		prtFn.dot2 = new PunctuationPeriod();
		prtFn.dot2.setPresent(true);

		if (newLine)
		{
			prtFn.PRINT = new Java_KeywordChoice("println");
		}
		else
		{
			prtFn.PRINT = new Java_KeywordChoice("print");
		}

		prtFn.leftParen = new PunctuationLeftParen();
		prtFn.rightParen = new PunctuationRightParen();

		prtFn.expr = line;
		if (line != null)
		{
			prtFn.expr.setPresent(true);
		}

		prtFn.setTransformationSource(source);
		return Java_Generator.wrapExpression(prtFn);
	}
}
