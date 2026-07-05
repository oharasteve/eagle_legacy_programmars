// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL.Statements;

import com.eagle.generate.AssignmentEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Variable;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class TCL_IncrStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("TclCmd/incr.html") TCL_Keyword INCR = new TCL_Keyword("incr");
	public @S(20) TCL_Variable var;
	public @S(30) @OPT TCL_Expression amount;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int x = 1;
		if (amount != null && amount.isPresent())
		{
			x = interpreter.getIntValue(amount);
		}

		int prev = interpreter.getIntValue(var);
		int newV = prev + x;
		EagleInteger val = new EagleInteger(newV);
		interpreter.setSymbol(var, var.id.getValue(), val);
	}

	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression subscrExpr = null;
		AbstractExpression value;
		if (amount != null && amount.isPresent())
		{
			value = transformer.transformExpression(generator, amount);
		}
		else
		{
			value = generator.newNumberExpression("1", INCR);
		}
		return generator.newAssignmentExpression(var.id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, AssignmentEnum.PLUS_EQUALS, value, this);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression asgExpr = this.transformExpression(transformer, generator);
		AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
		return exprStmt;
	}
}
