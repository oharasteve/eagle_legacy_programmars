// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class TCL_BracketLindex extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) TCL_Keyword LINDEX = new TCL_Keyword("lindex");
	public @S(30) TCL_Expression arrayExpr;
	public @S(40) TCL_Expression index;
	public @S(50) PunctuationRightBracket rightBracket;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(arrayExpr);
		EagleArray array = (EagleArray) value;
		int i = interpreter.getIntValue(index);
		interpreter.pushEagleValue(array.getValue(i));
	}
}
