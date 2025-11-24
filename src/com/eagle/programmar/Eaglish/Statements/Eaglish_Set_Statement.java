// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Eaglish_Set_Statement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Eaglish_Keyword SET = new Eaglish_Keyword("SET");
	public @S(20) Eaglish_Variable_Definition var;
	public @S(30) PunctuationEquals equals;
	public @S(40) Eaglish_Expression expression;
	public @S(50) Eaglish_EndOfLine eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expression);
		interpreter.setSymbol(var, var.getValue(), val);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AssignmentEnum asg = AssignmentEnum.EQUALS;
		AbstractExpression subscrExpr = null;
		AbstractExpression value = transformer.transformExpression(generator, expression);
		AbstractExpression asgExpr = generator.newAssignmentExpression(var.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
		return generator.newExpressionStatement(asgExpr, this);
	}
}
