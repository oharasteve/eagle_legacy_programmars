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
	public @S(10) @OPT CSharp_Keyword SYSTEM = new CSharp_Keyword("System");
	public @S(20) @OPT @NOSPACE PunctuationPeriod dot1;
	public @S(30) @NOSPACE CSharp_Keyword MATH = new CSharp_Keyword("Math");
	public @S(40) @NOSPACE PunctuationPeriod dot2;
	public @S(50) @NOSPACE CSharp_MathChoice choice;
	
	public static class CSharp_MathChoice extends TokenChooser
	{
		public @CHOICE CSharp_MathPowFunc XXmathPowFunction;
		public @CHOICE CSharp_MathMinMaxFunc XXmathMinMaxFunction;
	}
	
	public static CSharp_MathFunction wrapFunction(AbstractToken choice, AbstractToken source)
	{
		CSharp_MathFunction func = new CSharp_MathFunction();
		func.SYSTEM.setPresent(true);
		func.dot1 = new PunctuationPeriod();
		func.dot1.setPresent(true);
		func.dot2 = new PunctuationPeriod();
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
