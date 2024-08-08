// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Javascript;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_Data extends TokenSequence implements EagleRunnable
{
	public @S(10) Javascript_Type type;
	public @S(20) Javascript_Variable_Definition var;
	public @S(30) @OPT Javascript_InitData init;
	public @S(40) @OPT TokenList<Javascript_More_Variables> moreVars;
	public @S(50) @OPT PunctuationSemicolon semicolon;

	public static class Javascript_InitData extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Javascript_Expression expr;
	}

	public static class Javascript_More_Variables extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT TokenList<Javascript_Comment> comments;
		public @S(30) Javascript_Variable_Definition var;
		public @S(40) @OPT Javascript_InitData init;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(init.expr);
		interpreter.setSymbol(var, var.toString(), value);
	}
}
