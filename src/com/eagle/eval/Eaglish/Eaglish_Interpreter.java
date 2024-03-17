// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.eval.Eaglish;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.parsers.ParserManager;
import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Eaglish_Interpreter extends EagleInterpreter
 {
 	public Eaglish_Interpreter(ParserManager parser, EagleSymbolTable symbolTable)
 	{
 		super(parser, symbolTable);
 	}
 	
 	@Override
 	public void evaluateExpression(AbstractExpression abs_expr)
 	{
 		Eaglish_Expression expr = (Eaglish_Expression) abs_expr;
		AbstractToken which = expr.getWhich();
		
		if (which instanceof EagleRunnable)
		{
			EagleRunnable runnable = (EagleRunnable) which;
			runnable.interpret(this);
		}
		else
		{
			throw new RuntimeException("Unable to evaulate expression " + (which.getClass().getName()));
		}
 	}
}
