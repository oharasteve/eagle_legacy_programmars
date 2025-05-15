// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 27, 2024

package com.eagle.programmar.Lisp.Operators;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Lisp.Lisp_List;
import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Lisp_Variable;
import com.eagle.programmar.Lisp.Symbols.Lisp_Identifier_Reference;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_NthOperator extends TokenSequence implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Lisp_Keyword NTH = new Lisp_Keyword("NTH");
	public @S(30) Lisp_Expression index;
	public @S(40) Lisp_Expression list;
	public @S(50) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (list.getWhich() instanceof Lisp_List)
		{
			Lisp_List array = (Lisp_List) list.getWhich();
			int k = interpreter.getIntValue(index);
			EagleValue val = interpreter.getEagleValue(array.exprs._elements.get(k));
			interpreter.pushEagleValue(val);
			return;
		}

		if (list.getWhich() instanceof Lisp_Variable)
		{
			Lisp_Variable var = (Lisp_Variable) list.getWhich();
			if (var.getWhich() instanceof Lisp_Identifier_Reference)
			{
				Lisp_Identifier_Reference id = (Lisp_Identifier_Reference) var.getWhich();
				EagleValue value = interpreter.findSymbol(id.getValue());
				if (value.isArray())
				{
					EagleArray array = (EagleArray) value;
					int k = interpreter.getIntValue(index);
					EagleValue val = array.getValue(k);
					interpreter.pushEagleValue(val);
					return;
				}
			}
		}

		throw new RuntimeException("NTH requires an index and a list, not " + list.getWhich() + " " + index.getWhich());
	}
}
