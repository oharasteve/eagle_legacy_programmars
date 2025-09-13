// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Type;
import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.programmar.Algol68.Symbols.Algol68_Identifier_Reference;
import com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Algol68_Assignment extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @OPT Algol68_Type type;
	public @S(20) Algol68_Variable var;
	public @S(30) Algol68_PunctuationChoice operator = new Algol68_PunctuationChoice("=", "+=", ":=", "+:=");
	public @S(40) Algol68_Expression expression;
	public @S(50) @OPT PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expression);
		Algol68_Identifier_Reference id = var.vars.first();
		
		EagleValue v;
		switch (operator.getValue())
		{
		case "=", ":=":
			v = val;
			break;
		case "+=", "+:=":
			EagleValue var1 = interpreter.findSymbol(id.toString());
			v = new EagleInteger(var1.forceIntegerValue() + val.forceIntegerValue());
			break;
		default:
			throw new RuntimeException("Unable to handle " + operator.getValue());
		}

		interpreter.setSymbol(var, id.getValue(), v);
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
		case "+=", "+:=":
			asg = AssignmentEnum.PLUS_EQUALS;
			break;
		case "-=":
			asg = AssignmentEnum.MINUS_EQUALS;
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
		}

		AbstractExpression value = transformer.transformExpression(generator, expression);
		AbstractExpression asgExpr = generator.newAssignmentExpression(var.vars.first().getValue(),
				SubscriptEnum.FIRST_IS_ZERO, null, asg, value, this);
		AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
		return exprStmt;
	}
}
