// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 4, 2024

package com.eagle.programmar.Java.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_ExitStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) @NEWLINE Java_Keyword SYSTEM = new Java_Keyword("System");
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NEWLINE Java_Keyword EXIT = new Java_Keyword("exit");
	public @S(40) @NOSPACE Java_Expression code;
	public @S(50) @NOSPACE PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		interpreter._exitCode = interpreter.getIntValue(code);
		return Eagle_Statement_Result.BREAK;
	}
	
	public static Java_ExitStatement newExitStatement(AbstractExpression code, AbstractToken source)
	{
		Java_ExitStatement stmt = new Java_ExitStatement();
		stmt.code = (Java_Expression) code;
		stmt.setTransformationSource(source);
		return stmt;
	}
}
