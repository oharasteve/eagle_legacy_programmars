// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2025

package com.eagle.programmar.Python.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Expressions.Python_VariableExpression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_EndsWith_Function extends PrimaryOperator
		implements EagleRunnable
{
	public @S(10) Python_Variable string;
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Python_Keyword ENDSWITH = new Python_Keyword("endswith");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Python_Expression pattern;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(string);
		String patt = interpreter.getStrValue(pattern);
		interpreter.pushBool(str.endsWith(patt));
	}
	
	public Python_Expression generateEndsWith(Python_Expression expr, Python_Expression patt,
			AbstractToken source)
	{
		AbstractToken token = expr.getWhich();
		if (! (token instanceof Python_VariableExpression))
		{
			throw new RuntimeException("Python endswith must be a variable, not " + token);
		}
		
		Python_VariableExpression varExpr = (Python_VariableExpression) token;
		this.string = varExpr.variable;
		this.dot = new PunctuationPeriod();
		this.leftParen = new PunctuationLeftParen();
		this.pattern = patt;
		this.rightParen = new PunctuationRightParen();
		
		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}
