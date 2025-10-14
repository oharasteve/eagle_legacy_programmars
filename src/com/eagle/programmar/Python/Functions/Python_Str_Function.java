// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.Operator1Metrics.Oper1Types;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Str_Function extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Keyword STR = new Python_Keyword("str");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE Python_Expression expression;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expression);
		interpreter.pushStr(str);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expression);
		return generator.newStringFunction(null, theExpr, this);
	}
	
	public Python_Expression generateString(Oper1Types types, Python_Expression expr, AbstractToken source)
	{
		Python_Str_Function str = new Python_Str_Function();
		str.leftParen = new PunctuationLeftParen();
		str.rightParen = new PunctuationRightParen();
		if (expr.getWhich() instanceof Python_Parenthesized_Expression)
		{
			// Don't create a second set of parens
			Python_Parenthesized_Expression parens = (Python_Parenthesized_Expression) expr.getWhich();
			str.expression = parens.list.expr;
		}
		else
		{
			str.expression = expr;
		}
		
		str.setTransformationSource(source);
		return Python_Generator.wrapExpression(str);
	}
}
