// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2024

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_PrintFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) @NEWLINE @OPT CSharp_Keyword SYSTEM = new CSharp_Keyword("System");
	public @S(20) @NOSPACE @OPT PunctuationPeriod dot1;
	public @S(30) @NOSPACE CSharp_Keyword CONSOLE = new CSharp_Keyword("Console");
	public @S(40) @NOSPACE PunctuationPeriod dot2;
	public @S(50) @NOSPACE @OPT CSharp_KeywordChoice OUT = new CSharp_KeywordChoice("Error", "Out");
	public @S(60) @NOSPACE @OPT PunctuationPeriod dot3;
	public @S(70) @NOSPACE CSharp_KeywordChoice WRITE = new CSharp_KeywordChoice(
			"Flush", "ReadLine", "SetOut", "Write", "WriteLine");
	public @S(80) @NOSPACE PunctuationLeftParen leftParen;
	public @S(90) @NOSPACE @OPT SeparatedList<CSharp_Expression,PunctuationComma> exprs;
	public @S(100) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = interpreter.getStrValue(exprs.first());
		switch (WRITE.getValue())
		{
		case "Write":
			System.out.print(val);
			return;
		case "WriteLine":
			System.out.println(val);
			return;
		}
		
		throw new RuntimeException("Unexpected keyword: " + WRITE.getValue());
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		boolean newLine;
		switch (WRITE.getValue())
		{
		case "Write":
			newLine = false;
			break;
		case "WriteLine":
			newLine = true;
			break;
		default:
			throw new RuntimeException("Unexpected WRITE value: " + WRITE.getValue());
		}
		
		AbstractExpression value = transformer.transformExpression(generator, exprs.first());
		return generator.newPrintFunction(value, newLine, false, this);
	}

	public CSharp_Expression generatePrintFunc(CSharp_Expression line, boolean newLine,
			boolean toErr, AbstractToken source)
	{
		SYSTEM.setPresent(true);
		dot1 = new PunctuationPeriod();
		dot1.setPresent(true);
		dot2 = new PunctuationPeriod();
		dot2.setPresent(true);
		if (toErr)
		{
			OUT = new CSharp_KeywordChoice("Error");
		}
		else
		{
			OUT = new CSharp_KeywordChoice("Out");
		}
		OUT.setPresent(true);
		
		if (newLine)
		{
			WRITE = new CSharp_KeywordChoice("WriteLine");
		}
		else
		{
			WRITE = new CSharp_KeywordChoice("Write");
		}
		
		dot3 = new PunctuationPeriod();
		dot3.setPresent(true);
		leftParen = new PunctuationLeftParen();
		rightParen = new PunctuationRightParen();
		
		exprs = new SeparatedList<CSharp_Expression,PunctuationComma>();
		exprs.addPrimaryElement(line);
		
		setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
