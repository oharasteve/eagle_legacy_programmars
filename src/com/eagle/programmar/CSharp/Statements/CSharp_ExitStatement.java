// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2024

package com.eagle.programmar.CSharp.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_ExitStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @NEWLINE CSharp_Keyword SYSTEM = new CSharp_Keyword("System");
	public @S(20) @NOSPACE PunctuationPeriod dot1;
	public @S(30) @NOSPACE CSharp_Keyword ENVIRONMENT = new CSharp_Keyword("Environment");
	public @S(40) @NOSPACE PunctuationPeriod dot2;
	public @S(50) @NOSPACE CSharp_Keyword EXIT = new CSharp_Keyword("Exit");
	public @S(60) @NOSPACE PunctuationLeftParen leftParen;
	public @S(70) @NOSPACE @OPT CSharp_Expression code;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;
	public @S(90) @NOSPACE PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		interpreter._exitCode = interpreter.getIntValue(code);
		return Eagle_Statement_Result.BREAK;
	}
	
	public static CSharp_ExitStatement newExitStatement(AbstractExpression code, AbstractToken source)
	{
		CSharp_ExitStatement stmt = new CSharp_ExitStatement();
		stmt.code = (CSharp_Expression) code;
		stmt.setTransformationSource(source);
		return stmt;
	}
}
