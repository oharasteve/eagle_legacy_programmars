// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2026

package com.eagle.programmar.Lisp.Specials;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleString;
import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Lisp_List;
import com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Lisp_QuoteList extends PrimaryOperator  implements EagleRunnable
{
	public @S(10) Lisp_Punctuation quote = new Lisp_Punctuation('\'');
	public @S(20) Lisp_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (expr.getWhich() instanceof Lisp_List)
		{
			EagleArray array = new EagleArray();
			Lisp_List list = (Lisp_List) expr.getWhich();
			for (Lisp_Expression item : list.exprs._elements)
			{
				String value = interpreter.getStrValue(item);
				array.addValue(new EagleString(value));
			}

			interpreter.pushEagleValue(array);
		}
	}
}
