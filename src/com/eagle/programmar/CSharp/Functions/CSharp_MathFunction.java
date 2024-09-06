// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 4, 2024

package com.eagle.programmar.CSharp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class CSharp_MathFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CSharp_Keyword MATH = new CSharp_Keyword("Math");
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE CSharp_MathChoice choice;
	
	public static class CSharp_MathChoice extends TokenChooser
	{
		public @CHOICE CSharp_MathPowFunc mathPowFunction;
	}
	
	static CSharp_MathFunction wrapFunction(AbstractToken choice, AbstractToken source)
	{
		CSharp_MathFunction func = new CSharp_MathFunction();
		func.dot = new PunctuationPeriod();
		func.choice = new CSharp_MathChoice();
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
