// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Symbols.Go_Identifier_Reference;
import com.eagle.programmar.Go.Terminals.Go_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Go_AssignmentExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Go_Expression varExpr = new Go_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Go_PunctuationChoice equals = new Go_PunctuationChoice("=", ":=", "*=", "/=", "%=", "+=", "-=");
	public @S(30) Go_Expression expr = new Go_Expression(this, AllowedPrecedence.ATLEAST);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (!(varExpr.getWhich() instanceof Go_VariableExpression))
		{
			throw new RuntimeException("Can only handle simple assignments, not  " + varExpr.getWhich());
		}

		Go_Variable variable = ((Go_VariableExpression) varExpr.getWhich()).variable;
		EagleValue val = interpreter.getEagleValue(expr);
		Go_Identifier_Reference id = variable.vars.first();
		switch (equals.getValue())
		{
		case "=", ":=":
			interpreter._symbolTable.setSymbol(id.getFileName(), id.getStartLine(), id.getStartChar(), id.toString(), val);
			return;
		case "+=":
			EagleValue oldValue = interpreter._symbolTable.findSymbol(id.getValue());
			int newVal = val.forceIntegerValue() + oldValue.forceIntegerValue();
			interpreter._symbolTable.setSymbol(id.getFileName(), id.getStartLine(), id.getStartChar(), id.toString(),
					new EagleInteger(newVal));
			return;
		}
		throw new RuntimeException("Unable to handle equality operator: " + equals.getValue());
	}
}
