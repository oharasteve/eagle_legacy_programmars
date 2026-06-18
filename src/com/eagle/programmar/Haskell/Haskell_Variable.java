// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 12, 2026

package com.eagle.programmar.Haskell;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Haskell.Symbols.Haskell_Identifier_Reference;
import com.eagle.tokens.TokenSequence;

public class Haskell_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) Haskell_Identifier_Reference id;
//	public @S(20) @OPT Haskell_Subscript subscript;

//	public static class Haskell_Subscript extends TokenSequence
//	{
//		public @S(10) PunctuationLeftBracket leftBracket;
//		public @S(20) Haskell_Expression expr;
//		public @S(30) PunctuationRightBracket rightBracket;
//	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(id.getValue());
		interpreter.pushEagleValue(value);
	}
}
