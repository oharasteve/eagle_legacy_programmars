// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 12, 2026

package com.eagle.programmar.TCL.Statements;

import com.eagle.generate.AssignmentEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
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

public class TCL_AppendStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("TclCmd/append.html") TCL_Keyword APPEND = new TCL_Keyword("append");
	public @S(20) TCL_Variable var;
	public @S(30) TCL_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue oldValue = interpreter.findSymbol(var.id.getValue());
		String val = interpreter.getStrValue(expr);
		EagleString newValue = new EagleString(oldValue.forceStringValue() + val);
		interpreter.setSymbol(var, var.id.getValue(), newValue);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression subscrExpr = null;
		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractExpression newAsg = generator.newAssignmentExpression(var.id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, AssignmentEnum.PLUS_EQUALS, value, this);
		return generator.newExpressionStatement(newAsg, null);
	}
}
