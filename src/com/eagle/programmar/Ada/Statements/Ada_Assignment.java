// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Ada_Variable;
import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Ada_Assignment extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Ada_Variable variable;
	public @S(20) Ada_PunctuationChoice equals = new Ada_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
	public @S(30) Ada_Expression expr;
	public @S(40) PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ada_Identifier_Reference id = variable.vars.first();
		EagleValue val = interpreter.getEagleValue(expr);

		EagleValue v;
		switch (equals.getValue())
		{
		case "=", ":=":
			v = val;
			break;
		case "+=":
			EagleValue var1 = interpreter.findSymbol(id.toString());
			v = new EagleInteger(var1.forceIntegerValue() + val.forceIntegerValue());
			break;
		case "-=":
			EagleValue var2 = interpreter.findSymbol(id.toString());
			v = new EagleInteger(var2.forceIntegerValue() - val.forceIntegerValue());
			break;
		case "*=":
			EagleValue var3 = interpreter.findSymbol(id.toString());
			v = new EagleInteger(var3.forceIntegerValue() * val.forceIntegerValue());
			break;
		case "/=":
			EagleValue var4 = interpreter.findSymbol(id.toString());
			v = new EagleInteger(var4.forceIntegerValue() / val.forceIntegerValue());
			break;
		default:
			throw new RuntimeException("Unable to handle " + equals.getValue());
		}

		interpreter.setSymbol(variable, id.getValue(), v);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		AssignmentEnum asg;
		switch (equals.getValue())
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
			throw new RuntimeException("Unexpected assignment operator: " + equals.getValue());
		}

		AbstractExpression subscrExpr = null;
		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractExpression asgExpr = generator.newAssignmentExpression(variable.vars.first().getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
		return generator.newExpressionStatement(asgExpr, this);
	}
}
