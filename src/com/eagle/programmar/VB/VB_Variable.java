// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 15, 2011

package com.eagle.programmar.VB;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class VB_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) VB_Identifier_Reference var;
	public @S(20) @OPT VB_Subscript subscript;
	public @S(30) @OPT TokenList<VB_VariableField> dotFields;

	public static class VB_VariableField extends TokenSequence
	{
		public @S(10) PunctuationPeriod dot;
		public @S(20) VB_Identifier_Reference var;
		public @S(30) @OPT VB_Subscript subscript;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter._symbolTable.findSymbol(var.toString());
		interpreter.pushEagleValue(value);
	}
}
