// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Julia_Variable;
import com.eagle.programmar.Julia.Symbols.Julia_Identifier_Reference;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Julia_Assignment extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @OPT Julia_Keyword GLOBAL = new Julia_Keyword("global");
	public @S(20) Julia_Variable variable;
	public @S(30) Julia_PunctuationChoice operator = new Julia_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
	public @S(40) Julia_Expression expression;
	public @S(50) Julia_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Julia_Identifier_Reference id = variable.vars.first();
		switch (operator.getValue())
		{
		case "=":
			EagleValue val = interpreter.getEagleValue(expression);
			interpreter.setSymbol(id, id.getValue(), val);
			break;
		case "+=":
			int newVal1 = interpreter.getIntValue(expression);
			EagleValue oldVar1 = interpreter.findSymbol(id.getValue());
			EagleInteger newValue1 = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
			interpreter.setSymbol(id, id.getValue(), newValue1);
			break;
		case "-=":
			int newVal2 = interpreter.getIntValue(expression);
			EagleValue oldVar2 = interpreter.findSymbol(id.getValue());
			EagleInteger newValue2 = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
			interpreter.setSymbol(id, id.getValue(), newValue2);
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
		case "=":
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
		if (variable.subscript != null && variable.subscript.isPresent())
		{
			subscrExpr = transformer.transformExpression(generator, variable.subscript.expr);
		}
		
		AbstractExpression value = transformer.transformExpression(generator, expression);
		Julia_Identifier_Reference id = variable.vars.first();
		AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
		return generator.newExpressionStatement(asgExpr, this);
	}
}
