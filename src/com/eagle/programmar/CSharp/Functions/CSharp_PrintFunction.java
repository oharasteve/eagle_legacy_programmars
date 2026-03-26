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
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.TypeEnum;

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
	public @S(80) @NOSPACE @OPT CSharp_ConsoleWriteArgs args;
	
	public static class CSharp_ConsoleWriteArgs extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationLeftParen leftParen;
		public @S(20) @NOSPACE @OPT SeparatedList<CSharp_Expression, PunctuationComma> exprs;
		public @S(30) @NOSPACE PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = interpreter.getStrValue(args.exprs.first());
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
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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

		AbstractExpression value = transformer.transformExpression(generator, args.exprs.first());
		return generator.newPrintFunction(value, TypeEnum.STRING, newLine, false, this);
	}

	public static CSharp_Expression generatePrintFunc(CSharp_Expression line, boolean newLine,
			boolean toErr, AbstractToken source)
	{
		CSharp_PrintFunction prtFn = new CSharp_PrintFunction();
		prtFn.SYSTEM.setPresent(true);
		prtFn.dot1 = new PunctuationPeriod();
		prtFn.dot1.setPresent(true);
		prtFn.dot2 = new PunctuationPeriod();
		prtFn.dot2.setPresent(true);
		if (toErr)
		{
			prtFn.OUT = new CSharp_KeywordChoice("Error");
		}
		else
		{
			prtFn.OUT = new CSharp_KeywordChoice("Out");
		}
		prtFn.OUT.setPresent(true);

		if (newLine)
		{
			prtFn.WRITE = new CSharp_KeywordChoice("WriteLine");
		}
		else
		{
			prtFn.WRITE = new CSharp_KeywordChoice("Write");
		}

		prtFn.dot3 = new PunctuationPeriod();
		prtFn.dot3.setPresent(true);
		prtFn.args = new CSharp_ConsoleWriteArgs();
		prtFn.args.setPresent(true);
		prtFn.args.leftParen = new PunctuationLeftParen();
		prtFn.args.rightParen = new PunctuationRightParen();

		prtFn.args.exprs = new SeparatedList<CSharp_Expression, PunctuationComma>();
		prtFn.args.exprs.addPrimaryElement(line);

		prtFn.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(prtFn);
	}
}
