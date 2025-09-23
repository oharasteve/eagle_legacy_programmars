// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 17, 2024

package com.eagle.programmar.AWK.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleHash;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Variable;
import com.eagle.programmar.AWK.AWK_Variable.AWK_VarSubscript;
import com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class AWK_Assignment extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) AWK_Variable variable;
	public @S(20) AWK_PunctuationChoice equals = new AWK_PunctuationChoice("=", "+=", "-=", "*=", "/=");
	public @S(30) AWK_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue var = interpreter.findSymbol(variable.id.toString());
		EagleValue val = interpreter.getEagleValue(expr);

		if (variable.subscripts != null && variable.subscripts.size() == 1)
		{
			EagleHash hash = (EagleHash) var;
			if (hash == null)
			{
				hash = new EagleHash();
				interpreter.setSymbol(variable, variable.id.getValue(), hash);
			}
			AWK_VarSubscript sub = variable.subscripts.first();
			Integer key = interpreter.getIntValue(sub.expr);
			hash.putValue(key, val);
		}
		else
		{
			EagleValue v;
			switch (equals.getValue())
			{
			case "=":
				v = val;
				break;
			case "+=":
				v = new EagleInteger(var.forceIntegerValue() + val.forceIntegerValue());
				break;
			case "-=":
				v = new EagleInteger(var.forceIntegerValue() - val.forceIntegerValue());
				break;
			case "*=":
				v = new EagleInteger(var.forceIntegerValue() * val.forceIntegerValue());
				break;
			case "/=":
				v = new EagleInteger(var.forceIntegerValue() / val.forceIntegerValue());
				break;
			default:
				throw new RuntimeException("Unable to handle " + equals.getValue());
			}

			interpreter.setSymbol(variable, variable.id.getValue(), v);
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		AssignmentEnum asg;
		switch (equals.getValue())
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
			throw new RuntimeException("Unexpected assignment operator: " + equals.getValue());
		}

		String varName = variable.id.getValue();
		if (varName.equals("true") || varName.equals("false"))
		{
			// Sorry, cannot redefine true or false
			return null;
		}
		
		AbstractExpression value = transformer.transformExpression(generator, expr);
		if (variable.subscripts != null && variable.subscripts.size() == 1)
		{
			AWK_VarSubscript varSub = variable.subscripts.first();
			AbstractExpression subscrExpr = transformer.transformExpression(generator, varSub.expr);
			AbstractExpression hashExpr = generator.newHashAssignment(varName, subscrExpr, value, this);
			return generator.newExpressionStatement(hashExpr, this);
		}

		AbstractExpression asgExpr = generator.newAssignmentExpression(varName,
				SubscriptEnum.FIRST_IS_ZERO, null, asg, value, this);
		return generator.newExpressionStatement(asgExpr, this);
	}
}