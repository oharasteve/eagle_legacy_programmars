// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_RelationalOperator;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class COBOL_OrCondition extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) COBOL_Keyword OR = new COBOL_Keyword("OR");
	public @S(30) @OPT COBOL_RelationalOperator relationalOperator;
	public @S(40) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		boolean rightValue = interpreter.getBoolValue(right);
		interpreter.pushBool(leftValue || rightValue);
	}
}
