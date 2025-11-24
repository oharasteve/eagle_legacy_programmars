// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 14, 2025

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Eaglish_Variable;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Eaglish_Subtract_Statement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Eaglish_Keyword SUBTRACT = new Eaglish_Keyword("SUBTRACT");
	public @S(20) Eaglish_Expression expr;
	public @S(30) Eaglish_Keyword FROM = new Eaglish_Keyword("FROM");
	public @S(40) Eaglish_Variable var;
	public @S(50) Eaglish_EndOfLine eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int x = interpreter.getIntValue(expr);
		int prev = interpreter.getIntValue(var);
		EagleInteger val = new EagleInteger(prev - x);

		AbstractToken which = var.var.getWhich();
		if (which instanceof Eaglish_Identifier_Reference)
		{
			Eaglish_Identifier_Reference id = (Eaglish_Identifier_Reference) which;
			interpreter.setSymbol(var, id.getValue(), val);
			return;
		}
		throw new RuntimeException("Unable to process " + which);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractToken which = var.var.getWhich();
		if (!(which instanceof Eaglish_Identifier_Reference))
		{
			throw new RuntimeException("Can only subtract from variables");
		}
		Eaglish_Identifier_Reference id = (Eaglish_Identifier_Reference) which;

		AbstractExpression subscr = null;
		AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscr, AssignmentEnum.MINUS_EQUALS, value, this);
		return generator.newExpressionStatement(asgExpr, this);
	}
}
