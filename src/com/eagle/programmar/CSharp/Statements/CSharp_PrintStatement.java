// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2024

package com.eagle.programmar.CSharp.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class CSharp_PrintStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable, EagleTransformableStatement
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
	public @S(110) @NOSPACE PunctuationSemicolon semicolon;

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
	public AbstractStatement transformStatement(EagleTransformer transformer,
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
		return generator.newPrintStatement(value, newLine, this);
	}

	public CSharp_Statement generatePrintStmt(CSharp_Expression line, boolean newLine,
			AbstractToken source)
	{
		CSharp_PrintStatement prt = new CSharp_PrintStatement();
		prt.SYSTEM.setPresent(true);
		prt.dot1 = new PunctuationPeriod();
		prt.dot1.setPresent(true);
		prt.dot2 = new PunctuationPeriod();
		prt.dot2.setPresent(true);
		prt.OUT = new CSharp_KeywordChoice("Out");
		prt.OUT.setPresent(true);
		
		if (newLine)
		{
			prt.WRITE = new CSharp_KeywordChoice("WriteLine");
		}
		else
		{
			prt.WRITE = new CSharp_KeywordChoice("Write");
		}
		
		prt.dot3 = new PunctuationPeriod();
		prt.dot3.setPresent(true);
		prt.leftParen = new PunctuationLeftParen();
		prt.rightParen = new PunctuationRightParen();
		
		prt.exprs = new SeparatedList<CSharp_Expression,PunctuationComma>();
		prt.exprs.addPrimaryElement(line);
		prt.semicolon = new PunctuationSemicolon();
		
		prt.setTransformationSource(source);
		return CSharp_Generator.wrapStatement(prt);
	}
}
