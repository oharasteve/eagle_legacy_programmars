// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 22, 2012

package com.eagle.programmar.VB.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class VB_WscriptEcho extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) VB_Keyword WSCRIPT = new VB_Keyword("wscript");
	public @S(20) PunctuationPeriod dot;
	public @S(30) VB_KeywordChoice ECHO = new VB_KeywordChoice("echo", "quit");
	public @S(40) VB_Expression expr;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue result = interpreter.getEagleValue(expr);
		switch (ECHO.toString())
		{
		case "echo":
			System.out.println(result.toString());
			return Eagle_Statement_Result.NORMAL;
		case "quit":
			interpreter._exitCode = result.forceIntegerValue();
			return Eagle_Statement_Result.BREAK;
		}
		throw new RuntimeException("Unable to handle " + ECHO.toString());
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		switch (ECHO.toString())
		{
		case "echo":
			AbstractExpression line = transformer.transformExpression(generator, expr);
			return generator.newPrintStatement(line, this);
		case "quit":
			AbstractExpression code = transformer.transformExpression(generator, expr);
			return generator.newExitStatement(code, this);
		}
		throw new RuntimeException("Unable to handle " + ECHO.toString());
	}
}
