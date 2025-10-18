// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Fortran_Variable;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Fortran_Assignment extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Fortran_Variable variable;
	public @S(20) PunctuationEquals equals;
	public @S(30) Fortran_Expression expression;
	public @S(40) Fortran_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expression);
		interpreter.setSymbol(variable, variable.var.toString(), val);
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression subscrExpr = null;
		AbstractExpression value = transformer.transformExpression(generator, expression);
		AbstractExpression asgExpr = generator.newAssignmentExpression(variable.var.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, AssignmentEnum.EQUALS, value, this);
		return generator.newExpressionStatement(asgExpr, this);
	}
}
