// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Variable.Perl_UserVariable;
import com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Perl_AssignmentExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Perl_Expression var = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Perl_PunctuationChoice operator = new Perl_PunctuationChoice(
			"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=", ".=");
	public @S(30) Perl_Expression expr;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		if (var.getWhich() instanceof Perl_VariableExpression)
		{
			Perl_VariableExpression varExpr = (Perl_VariableExpression) var.getWhich();
			Perl_UserVariable userVar = (Perl_UserVariable) varExpr.variable.getWhich();
			interpreter._symbolTable.setSymbol(userVar.id.getValue(), value);
		}
	}
}
