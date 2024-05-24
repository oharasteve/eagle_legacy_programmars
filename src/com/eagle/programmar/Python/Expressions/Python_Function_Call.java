// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Parameter_List;
import com.eagle.programmar.Python.Python_Params.Python_Param;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;

public class Python_Function_Call extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Python_Variable name;
	public @S(20) @NOSPACE TokenList<Python_Parameter_List> args;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Assume print(expr);
		Python_Param param = args.first().params.param;
		AbstractToken which = param.getWhich();
		if (which instanceof Python_Expression)
		{
			Python_Expression expr = (Python_Expression) which;
			String result = interpreter.getStrValue(expr);
			System.out.println(result);
		}
	}
}
