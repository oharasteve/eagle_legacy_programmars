// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.programmar.CSharp.CSharp_Argument.CSharp_ArgumentOut;
import com.eagle.programmar.CSharp.CSharp_ArgumentList;
import com.eagle.programmar.CSharp.CSharp_ArgumentList.CSharp_MoreArguments;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class CSharp_ClassCreationWithInitializers extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CSharp_Keyword NEW = new CSharp_Keyword("new");
	public @S(20) CSharp_Type jtype;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) @OPT @NOSPACE CSharp_ArgumentList valueList;
	public @S(50) @NOSPACE PunctuationRightBrace rightBrace;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();

		if (valueList.arg.isPresent())
		{
			AbstractToken token = valueList.arg.getWhich();
			if (token instanceof CSharp_ArgumentOut)
			{
				CSharp_ArgumentOut arg = (CSharp_ArgumentOut) token;
				array.addValue(interpreter.getEagleValue(arg.arg));
			}
		}
		
		if (valueList.moreArgs.isPresent())
		{
			for (CSharp_MoreArguments more : valueList.moreArgs._elements)
			{
				AbstractToken token = more.arg.getWhich();
				if (token instanceof CSharp_ArgumentOut)
				{
					CSharp_ArgumentOut arg = (CSharp_ArgumentOut) token;
					array.addValue(interpreter.getEagleValue(arg.arg));
				}
			}
		}
		
		interpreter.pushEagleValue(array);
	}
}
