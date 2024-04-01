// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Parameter_List;
import com.eagle.programmar.Python.Python_Parameter_List.Python_Parameters.Python_Params;
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
		AbstractToken what = args.first().params.getWhich();
		if (! (what instanceof Python_Params)) throw new RuntimeException("Unexpected arg: " + what.toString());
		Python_Params params = (Python_Params) what;
		EagleValue result = interpreter.getEagleValue(params.expr);
		System.out.println(result.toString());
	}
}
