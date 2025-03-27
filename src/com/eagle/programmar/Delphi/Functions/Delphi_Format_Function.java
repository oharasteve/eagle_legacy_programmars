// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Delphi.Delphi_Argument_List;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Format;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Delphi_Format_Function extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Delphi_Keyword FORMAT = new Delphi_Keyword("Format");
	public @S(20) Delphi_Argument_List arguments;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Delphi_Expression fmtExpr = arguments.exprs.getPrimaryElement(0);
		String fmt = interpreter.getStrValue(fmtExpr);
		Delphi_Expression arrayExpr = arguments.exprs.getPrimaryElement(1);
		ArrayList<EagleValue> values = interpreter.getArrayValue(arrayExpr);
		String formatted = Delphi_Format.format(fmt, values);
		interpreter.pushStr(formatted);
	}
}
