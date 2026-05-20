// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 4, 2024

package com.eagle.programmar.Java.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_MathFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_Keyword MATH = new Java_Keyword("Math");
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Java_MathChoice choice;

	public static class Java_MathChoice extends TokenChooser
	{
		public @CHOICE Java_MathAbsFunc XXmathAbsFunction;
		public @CHOICE Java_MathFloorModFunc XXmathFloorModFunction;
		public @CHOICE Java_MathLogFunc XXmathLogFunction;
		public @CHOICE Java_MathMinMaxFunc XXmathMinMaxFunction;
		public @CHOICE Java_MathPowFunc XXmathPowFunction;
		public @CHOICE Java_MathRoundFunc XXmathRoundFunction;
	}

	public static Java_Expression wrapMathFunction(AbstractToken choice, AbstractToken source)
	{
		Java_MathFunction func = new Java_MathFunction();
		func.dot = new PunctuationPeriod();
		func.choice = new Java_MathChoice();
		func.choice.setWhich(choice);
		func.setTransformationSource(source);
		return Java_Generator.wrapExpression(func);
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(choice);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractToken which = choice.getWhich();
		if (which instanceof EagleTransformableExpression)
		{
			EagleTransformableExpression transf = (EagleTransformableExpression) which;
			return transf.transformExpression(transformer, generator);
		}
		throw new RuntimeException("Please make " + which + " transformable");
	}
}
