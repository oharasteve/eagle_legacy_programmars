// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Functions;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Delphi.Delphi_Argument_List;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Format;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Delphi_Builtin_Function extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Delphi_KeywordChoice name = new Delphi_KeywordChoice(
			"Copy", "Format", "Length", "Odd", "Pred", "Succ");
	public @S(20) Delphi_Argument_List arguments;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (name.toString())
		{
		case "Copy":
			if (arguments.exprs.getPrimaryCount() == 3)
			{
				String str = interpreter.getStrValue(arguments.exprs.getPrimaryElement(0));
				int sc = interpreter.getIntValue(arguments.exprs.getPrimaryElement(1)) - 1;
				int ec = interpreter.getIntValue(arguments.exprs.getPrimaryElement(2)) + sc;
				int nc = str.length();
				if (ec > nc) ec = nc; 
				interpreter.pushStr(str.substring(sc, ec));
				return;
			}
			break;
		case "Format":
			if (arguments.exprs.getPrimaryCount() == 2)
			{
				Delphi_Expression fmtExpr = arguments.exprs.getPrimaryElement(0);
				String fmt = interpreter.getStrValue(fmtExpr);

				Delphi_Expression arrayExpr = arguments.exprs.getPrimaryElement(1);
				ArrayList<EagleValue> values = interpreter.getArrayValue(arrayExpr);

				String formatted = Delphi_Format.format(fmt, values);
				interpreter.pushStr(formatted);
				return;
			}
			break;
		case "Length":
			if (arguments.exprs.getPrimaryCount() == 1)
			{
				String str = interpreter.getStrValue(arguments.exprs.getPrimaryElement(0));
				interpreter.pushInt(str.length());
				return;
			}
			break;
		}

		throw new RuntimeException("Can't handle BuiltIn's other than Format: " + name);
	}
}
