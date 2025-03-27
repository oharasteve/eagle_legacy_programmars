// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 13, 2015

package com.eagle.programmar.Template.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Template.Template_Expression;
import com.eagle.programmar.Template.Template_Variable;
import com.eagle.programmar.Template.Symbols.Template_Identifier_Reference;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Template_AssignmentStatement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) Template_Variable var;
	public @S(20) PunctuationEquals equals;
	public @S(30) Template_Expression expr;
	public @S(40) PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		Template_Identifier_Reference id = (Template_Identifier_Reference) var.getWhich();
		interpreter.setSymbol(id, id.getValue(), value);
	}
}
