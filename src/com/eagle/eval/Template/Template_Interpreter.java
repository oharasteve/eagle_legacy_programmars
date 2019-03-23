// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 13, 2015

package com.eagle.eval.Template;

import com.eagle.core.EagleInterpreter;
import com.eagle.parsers.ParserManager;
import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.programmar.Template.Template_Expression;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Template_Interpreter extends EagleInterpreter
{
	private Eval_Template_Expression evaluator = new Eval_Template_Expression();

	public Template_Interpreter(ParserManager parser, EagleSymbolTable symbolTable)
	{
		super(parser, symbolTable);
	}
	
	@Override
	protected void evaluateExpression(AbstractExpression expr)
	{
		evaluator.interpret((Template_Expression) expr, this);
	}
}
