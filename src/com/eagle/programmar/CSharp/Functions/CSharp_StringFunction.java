// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 14, 2025

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class CSharp_StringFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CSharp_Keyword STRING = new CSharp_Keyword("String");
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE CSharp_StringChoice choice;
	
	public static class CSharp_StringChoice extends TokenChooser
	{
		public @CHOICE CSharp_StringFormatFunc XXstringFormatFunction;
	}
	
	public static CSharp_StringFunction wrapStringFunction(AbstractToken choice, AbstractToken source)
	{
		CSharp_StringFunction func = new CSharp_StringFunction();
		func.dot = new PunctuationPeriod();
		func.choice = new CSharp_StringChoice();
		func.choice.setWhich(choice);
		func.setTransformationSource(source);
		return func;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(choice);
	}
}
