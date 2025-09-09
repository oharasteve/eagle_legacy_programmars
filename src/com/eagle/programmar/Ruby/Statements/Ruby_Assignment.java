// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Symbols.Ruby_Identifier_Reference;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;

public class Ruby_Assignment extends TokenSequence	
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Ruby_Variable var;
	public @S(20) Ruby_PunctuationChoice operator =
				new Ruby_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
	public @S(30) Ruby_Expression expr;
	public @S(40) Ruby_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ruby_Identifier_Reference id = var.vars.first();
		EagleValue val = interpreter.getEagleValue(expr);

		switch (operator.getValue())
		{
		case "=", ":=":
			interpreter.setSymbol(var, id.getValue(), val);
			break;
		case "+=":
			EagleValue oldValue1 = interpreter.findSymbol(id.getValue());
			int old1 = oldValue1.forceIntegerValue();
			interpreter.setSymbol(var, id.getValue(), new EagleInteger(old1 + val.forceIntegerValue()));
			break;
		case "-=":
			EagleValue oldValue2 = interpreter.findSymbol(id.getValue());
			int old2 = oldValue2.forceIntegerValue();
			interpreter.setSymbol(var, id.getValue(), new EagleInteger(old2 - val.forceIntegerValue()));
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AssignmentEnum asg;
		switch (operator.getValue())
		{
		case "=", ":=":
			asg = AssignmentEnum.EQUALS;
			break;
		case "+=":
			asg = AssignmentEnum.PLUS_EQUALS;
			break;
		case "-=":
			asg = AssignmentEnum.MINUS_EQUALS;
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
		}

		AbstractExpression subscrExpr = null;
//		if (var.subscript != null && var.subscript.isPresent())
//		{
//			subscrExpr = transformer.transformExpression(generator, var.subscript.expr);
//		}
		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractExpression asgExpr = generator.newAssignmentExpression(var.vars.first().getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
		AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
		return exprStmt;
	}
}
