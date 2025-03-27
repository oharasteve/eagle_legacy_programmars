// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Template.Template_Expression;
import com.eagle.programmar.Template.Symbols.Template_Identifier_Definition;
import com.eagle.programmar.Template.Terminals.Template_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Template_DataStatement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) Template_Keyword DATA = new Template_Keyword("data");
	public @S(20) Template_Identifier_Definition var;
	public @S(30) PunctuationEquals equals;
	public @S(40) Template_Expression expr;
	public @S(50) PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		interpreter.setSymbol(var, var.getValue(), value);
	}
}
