// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 4, 2024

package com.eagle.programmar.Python.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Python_QuitStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @NOSPACE Python_Keyword QUIT = new Python_Keyword("quit");
	public @S(20) @OPT Python_Expression code;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		interpreter._exitCode = interpreter.getIntValue(code);
		return Eagle_Statement_Result.BREAK;
	}

	public static Python_QuitStatement newQuitStatement(AbstractExpression code, AbstractToken source)
	{
		Python_QuitStatement stmt = new Python_QuitStatement();
		stmt.code = (Python_Expression) code;
		stmt.setTransformationSource(source);
		return stmt;
	}
}
