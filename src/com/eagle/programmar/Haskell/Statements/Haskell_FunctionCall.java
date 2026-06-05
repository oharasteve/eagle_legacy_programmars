// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 4, 2026

package com.eagle.programmar.Haskell.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Haskell_Variable;
import com.eagle.programmar.Haskell.Terminals.Haskell_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class Haskell_FunctionCall extends TokenSequence
	implements EagleRunnable, EagleTransformableStatementList
{
	public @S(10) Haskell_Variable variable;
	public @S(20) Haskell_Punctuation arrow = new Haskell_Punctuation("<-");
	public @S(30) Haskell_Expression expression;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expression);
		interpreter.setSymbol(variable.id, variable.id.getValue(), value);
	}
	
	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		AbstractExpression value = transformer.transformExpression(generator, expression);
		AbstractExpression asgExpr = generator.newAssignmentExpression(variable.id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, value, this);
		AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
		result.add(exprStmt);
		return result;
	}
}
