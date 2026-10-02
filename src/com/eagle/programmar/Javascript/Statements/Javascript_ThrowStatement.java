// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_ThrowStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("js_throw.asp") Javascript_Keyword THROW = new Javascript_Keyword("throw");
	public @S(20) Javascript_Expression expression;
	public @S(30) @OPT PunctuationComma comma;
	public @S(40) @OPT Javascript_Expression extraExpression;
	public @S(50) @OPT PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		throw new JsValues.JsThrow(JsRuntime.of(interpreter).eval(expression));
	}
}
