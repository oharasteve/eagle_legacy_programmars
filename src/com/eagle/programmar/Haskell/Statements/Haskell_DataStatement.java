// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Haskell_Type;
import com.eagle.programmar.Haskell.Symbols.Haskell_Data_Definition;
import com.eagle.programmar.Haskell.Symbols.Haskell_Identifier_Reference;
import com.eagle.programmar.Haskell.Terminals.Haskell_EndOfLine;
import com.eagle.programmar.Haskell.Terminals.Haskell_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Haskell_DataStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) Haskell_DataType type;
	public @S(20) Haskell_Data_Definition id;
	public @S(30) PunctuationEquals equals;
	public @S(40) Haskell_Expression expression;
	
	public static class Haskell_DataType extends TokenSequence
	{
		public @S(10) Haskell_Identifier_Reference id;
		public @S(20) Haskell_Punctuation colonColon = new Haskell_Punctuation("::");
		public @S(30) Haskell_Type type;
		public @S(50) Haskell_EndOfLine eoln;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expression);
		interpreter.setSymbol(id, id.getValue(), val);
	}
}
