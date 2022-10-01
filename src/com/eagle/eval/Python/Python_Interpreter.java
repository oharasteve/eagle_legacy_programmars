// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2022

package com.eagle.eval.Python;

import com.eagle.core.EagleInterpreter;
import com.eagle.parsers.ParserManager;
import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.tokens.interfaces.AbstractExpression;


public class Python_Interpreter extends EagleInterpreter
{
	private Eval_Python_Expression evaluator = new Eval_Python_Expression();
	
	public Python_Interpreter(ParserManager parser, EagleSymbolTable symbolTable)
	{
		super(parser, symbolTable);
	}
	
	@Override
	protected void evaluateExpression(AbstractExpression expr)
	{
		evaluator.interpret((Python_Expression) expr, this);
	}
}
