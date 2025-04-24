// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2024

package com.eagle.programmar.Java.Statements;

import java.io.PrintStream;
import java.util.ArrayList;

import com.eagle.generate.Statements.Eagle_Generate_Print;
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

public class Java_PrintStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable,
				Eagle_Generate_Print<Java_Statement, Java_Expression>
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
	public Java_Statement generatePrint(ArrayList<AbstractExpression> pieces,
			boolean newLine, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
	}
	
	@Override
	public Java_Statement generatePrint1(Java_Expression line, boolean newLine,
			AbstractToken source)
	{
		Java_PrintStatement stmt = new Java_PrintStatement();
		stmt.dot1 = new PunctuationPeriod();
		stmt.dot1.setPresent(true);
		stmt.OUT = new Java_KeywordChoice("out");
		stmt.dot2 = new PunctuationPeriod();
		stmt.dot2.setPresent(true);
		
		if (newLine)
		{
			stmt.PRINT = new Java_KeywordChoice("println");
		}
		else
		{
			stmt.PRINT = new Java_KeywordChoice("print");
		}
		
		stmt.leftParen = new PunctuationLeftParen();
		stmt.rightParen = new PunctuationRightParen();

		stmt.expr = line;
		stmt.expr.setPresent(true);
		stmt.semicolon = new PunctuationSemicolon();
		
		stmt.setTransformationSource(source);
		return Java_Generator.wrapStatement(stmt);
	}
}
