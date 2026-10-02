// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Javascript_TrueFalseExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Javascript_Punctuation questionMark = new Javascript_Punctuation('?');
	public @S(30) @OPT TokenList<Javascript_Comment> comments1;
	public @S(40) Javascript_Expression middle = new Javascript_Expression(this, AllowedPrecedence.ANY);
	public @S(50) PunctuationColon colon;
	public @S(60) @OPT TokenList<Javascript_Comment> comments2;
	public @S(70) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		interpreter.pushEagleValue(JsValues.truthy(rt.eval(left)) ? rt.eval(middle) : rt.eval(right));
	}
}
