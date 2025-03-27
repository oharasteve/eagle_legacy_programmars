// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.C_ArgumentList;
import com.eagle.programmar.C.C_ArgumentList.C_MoreArgument;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class C_ExpressionList extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT C_ArgumentList valueList;
	public @S(30) @OPT C_Comment comment;
	public @S(40) PunctuationRightBrace rightBrace;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();
		EagleValue val = interpreter.getEagleValue(valueList.arg.getWhich());
		array.addValue(val);
		for (C_MoreArgument arg : valueList.moreArgs._elements)
		{
			val = interpreter.getEagleValue(arg.arg);
			array.addValue(val);
		}
		interpreter.pushEagleValue(array);
	}
}
