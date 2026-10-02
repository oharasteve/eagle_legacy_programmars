// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Element;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_DoStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("js_loop_while.asp") Javascript_Keyword DO = new Javascript_Keyword("do");
	public @S(20) Javascript_Element doStatement;
	public @S(30) Javascript_Keyword WHILE = new Javascript_Keyword("while");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Javascript_Expression condition;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) @OPT PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		while (true)
		{
			Eagle_Statement_Result result = interpreter.tryToInterpret(doStatement);
			if (result == Eagle_Statement_Result.BREAK) return Eagle_Statement_Result.NORMAL;
			if (result == Eagle_Statement_Result.RETURN) return result;
			if (!JsValues.truthy(rt.eval(condition))) return Eagle_Statement_Result.NORMAL;
		}
	}
}
