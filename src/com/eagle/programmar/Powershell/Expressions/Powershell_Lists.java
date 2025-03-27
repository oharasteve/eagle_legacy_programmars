// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_ExpressionList;
import com.eagle.programmar.Powershell.Powershell_ExpressionList.Powershell_MoreExpression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_Lists extends PrimaryOperator implements EagleRunnable
{
	public @S(10) @OPT Powershell_Punctuation at = new Powershell_Punctuation("@");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT Powershell_ExpressionList expressions;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();
		EagleValue val = interpreter.getEagleValue(expressions.expr);
		array.addValue(val);
		for (int i = 0; i < expressions.more.size(); i++)
		{
			Powershell_MoreExpression expr = expressions.more._elements.get(i);
			val = interpreter.getEagleValue(expr.expr);
			array.addValue(val);
		}
		interpreter.pushEagleValue(array);
	}
}
