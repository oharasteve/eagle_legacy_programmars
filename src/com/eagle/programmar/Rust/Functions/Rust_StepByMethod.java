// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, JMar 29, 2026

package com.eagle.programmar.Rust.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleRange;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_StepByMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_Keyword STEPBY = new Rust_Keyword("step_by");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Rust_Expression stepExpr;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleRange range = interpreter.getRangeValue(left);
		int step = interpreter.getIntValue(stepExpr);
		interpreter.pushEagleValue(new EagleRange(range._lowValue, range._highValue, true, step));
	}
		
	public static Rust_Expression generateStepBy(Rust_Expression range, Rust_Expression expr)
	{
		Rust_StepByMethod step = new Rust_StepByMethod();
		step.left = range;
		step.dot = new PunctuationPeriod();
		step.leftParen = new PunctuationLeftParen();
		step.stepExpr = expr;
		step.rightParen = new PunctuationRightParen();
		return Rust_Generator.wrapExpression(step);
	}
}
