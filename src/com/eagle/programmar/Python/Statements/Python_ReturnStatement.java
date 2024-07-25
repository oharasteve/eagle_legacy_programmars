// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_ExpressionList;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Python_ReturnStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("compound_stmts.html#function-definitions") @NOSPACE Python_Keyword RETURN =
			new Python_Keyword("return");
	public @S(20) @OPT Python_Keyword AWAIT = new Python_Keyword("await");
	public @S(30) @OPT Python_ExpressionList expressionList;
	public @S(40) @OPT Python_Comment comment;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expressionList.expressions.first());
		interpreter.pushEagleValue(val);
		return Eagle_Statement_Result.RETURN;
	}
}
