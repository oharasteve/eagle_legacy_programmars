// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 4, 2024

package com.eagle.programmar.Java.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Java_MathFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Java_Keyword MATH = new Java_Keyword("Math");
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Java_MathChoice choice;
	
	public static class Java_MathChoice extends TokenChooser
	{
		public @CHOICE Java_MathPowFunc XXmathPowFunction;
		public @CHOICE Java_MathLogFunc XXmathLogFunction;
		public @CHOICE Java_MathRoundFunc XXmathRoundFunction;
		public @CHOICE Java_MathAbsFunc XXmathAbsFunction;
		public @CHOICE Java_MathMinMaxFunc XXmathMinMaxFunction;
	}
	
	static Java_MathFunction wrapFunction(AbstractToken choice, AbstractToken source)
	{
		Java_MathFunction func = new Java_MathFunction();
		func.dot = new PunctuationPeriod();
		func.choice = new Java_MathChoice();
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
