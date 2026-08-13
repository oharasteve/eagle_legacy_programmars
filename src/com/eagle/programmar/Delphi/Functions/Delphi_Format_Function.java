// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Delphi.Delphi_Argument_List;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_LiteralExpression;
import com.eagle.tokens.PrimaryOperator;

public class Delphi_Format_Function extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Delphi_Keyword FORMAT = new Delphi_Keyword("Format");
	public @S(20) Delphi_Argument_List argList;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		ArgumentsMetrics metrics = null;
		String value = Delphi_LiteralExpression.interpret(interpreter, argList, metrics);
		interpreter.pushStr(value);
	}
}
