// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

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

public class C_AssignmentExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) C_Expression var = new C_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) C_PunctuationChoice equals = new C_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=",
			">>>=", "&=", "^=", "|=");
	public @S(30) @OPT @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro; // What the ...
	public @S(40) C_Expression rightAsg = new C_Expression(this, AllowedPrecedence.ATLEAST);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(rightAsg);
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

		switch (equals.getValue())
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
}
