// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 13, 2015

package com.eagle.eval.Template;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.parsers.ParserManager;
import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.programmar.Template.Template_Expression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Template_Interpreter extends EagleInterpreter
{
	public Template_Interpreter(ParserManager parser, EagleSymbolTable symbolTable)
	{
		super(parser, symbolTable);
	}
	
	@Override
	protected void evaluateExpression(AbstractExpression abs_expr)
	{
		Template_Expression expr = (Template_Expression) abs_expr;
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
