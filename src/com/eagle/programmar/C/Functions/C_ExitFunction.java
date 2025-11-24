// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_ExitFunction extends PrimaryOperator implements EagleRunnableWithResult
{
	public @S(10) C_Keyword EXIT = new C_Keyword("exit");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Expression code; // 0 = ok, else error code number
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		int ret = interpreter.getIntValue(code);
		interpreter._exitCode = ret;
		// Can't really System.exit(ret) here or it messes up junit tests
		return Eagle_Statement_Result.THROW; // Good as any
	}
}
