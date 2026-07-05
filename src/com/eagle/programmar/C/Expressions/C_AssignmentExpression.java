// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.generate.AssignmentEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class C_AssignmentExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) C_Expression var = new C_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) C_PunctuationChoice operator = new C_PunctuationChoice(
			"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
	public @S(30) @OPT @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro; // What the ...
	public @S(40) C_Expression expr = new C_Expression(this, AllowedPrecedence.ATLEAST);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		if (!(var.getWhich() instanceof C_VariableExpression))
		{
			throw new RuntimeException("Can only handle simple assignments, not  " + var.getWhich());
		}

		C_Variable variable = ((C_VariableExpression) var.getWhich()).variable;
		AbstractToken which = variable.firstId.getWhich();
		if (!(which instanceof C_Identifier_Reference))
		{
			throw new RuntimeException("Unable to handle " + which + " now");
		}
		C_Identifier_Reference id = (C_Identifier_Reference) which;

		switch (operator.getValue())
		{
		case "=":
			interpreter.setSymbol(var, id.getValue(), val);
			break;
		case "+=":
			EagleValue oldValue1 = interpreter.findSymbol(id.getValue());
			int newValue1 = oldValue1.forceIntegerValue() + val.forceIntegerValue();
			interpreter.setSymbol(var, id.getValue(), new EagleInteger(newValue1));
			break;
		case "-=":
			EagleValue oldValue2 = interpreter.findSymbol(id.getValue());
			int newValue2 = oldValue2.forceIntegerValue() - val.forceIntegerValue();
			interpreter.setSymbol(var, id.getValue(), new EagleInteger(newValue2));
			break;
		default:
			throw new RuntimeException("Can only handle = and += right now");
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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

		if (!(var.getWhich() instanceof C_VariableExpression))
		{
			throw new RuntimeException("Can only assign variables");
		}
		C_VariableExpression variableExpr = (C_VariableExpression) var.getWhich();
		C_Variable theVar = variableExpr.variable;

		AbstractExpression subscrExpr = null;
		if (theVar.subscript != null && theVar.subscript.size() > 0)
		{
			subscrExpr = transformer.transformExpression(generator, theVar.subscript.first().expr);
		}

		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractToken which = theVar.firstId.getWhich();
		if (!(which instanceof C_Identifier_Reference))
		{
			throw new RuntimeException("Have to assign to a regular variable");
		}
		C_Identifier_Reference id = (C_Identifier_Reference) which;

		AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
		return asgExpr;
	}
}
