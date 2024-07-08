// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Java_ArgumentList.Java_MoreArguments;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Java_ClassCreationWithInitializers extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Java_Keyword NEW = new Java_Keyword("new");
	public @S(20) Java_Type jtype;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) @OPT Java_ArgumentList valueList;
	public @S(50) PunctuationRightBrace rightBrace;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();

		if (valueList.arg.isPresent())
		{
			Java_Expression expr = valueList.arg;
			array.addValue(interpreter.getEagleValue(expr));
		}
		
		if (valueList.moreArgs.isPresent())
		{
			for (Java_MoreArguments more : valueList.moreArgs._elements)
			{
				array.addValue(interpreter.getEagleValue(more.arg));
			}
		}
		
		interpreter.pushEagleValue(array);
	}
}
