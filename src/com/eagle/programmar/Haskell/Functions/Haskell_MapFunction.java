// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 6, 2026

package com.eagle.programmar.Haskell.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Expressions.Haskell_RangeExpression;
import com.eagle.programmar.Haskell.Statements.Haskell_Function;
import com.eagle.programmar.Haskell.Symbols.Haskell_Identifier_Reference;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.programmar.Haskell.Terminals.Haskell_Number;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;

public class Haskell_MapFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Haskell_Keyword MAP = new Haskell_Keyword("map");
	public @S(20) Haskell_Identifier_Reference funcName;
	public @S(30) Haskell_RangeExpression range;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fnName = funcName.getValue();
		AbstractFunction fn = interpreter.findFunction(fnName);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + fnName);
		}
		Haskell_Function func = (Haskell_Function) fn;

		EagleArray result = new EagleArray();
		int start = interpreter.getIntValue(range.start);
		int stop = interpreter.getIntValue(range.stop);
		ArrayList<Haskell_Expression> args = new ArrayList<Haskell_Expression>();
		Haskell_Number num = new Haskell_Number();
		Haskell_Expression expr = new Haskell_Expression();
		expr.setWhich(num);
		args.add(expr);
		for (int i = start; i <= stop; i++)
		{
			num.setValue(Integer.toString(i));
			func.call(interpreter, fnName, args);
			EagleValue val = interpreter.popEagleValue();
			result.addValue(val);
		}
		interpreter.pushEagleValue(result);
	}
}
