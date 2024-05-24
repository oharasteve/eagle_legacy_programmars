// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Bash;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Bash_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT Bash_Punctuation dollar = new Bash_Punctuation("$");
	public @S(20) Bash_Identifier_Reference id;
	public @S(30) @OPT Bash_Subscript subscript;

	public static class Bash_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Bash_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter._symbolTable.findSymbol(id.toString());
		interpreter.pushEagleValue(value);
	}
}
