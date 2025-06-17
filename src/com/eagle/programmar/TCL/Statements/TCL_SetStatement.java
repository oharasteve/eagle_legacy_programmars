// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.AssignmentEnum;
import com.eagle.generate.EagleGenerator.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Variable;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class TCL_SetStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("TclCmd/set.html") TCL_Keyword SET = new TCL_Keyword("set");
	public @S(20) TCL_Variable var;
	public @S(30) TCL_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.setSymbol(var, var.id.getValue(), val);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression subscrExpr = null;
		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractExpression asgExpr = generator.newAssignmentExpression(var.id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, AssignmentEnum.EQUALS, value, this);
		AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
		return exprStmt;
	}
}
