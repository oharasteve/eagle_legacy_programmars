// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 11, 2024

package com.eagle.programmar.Ada.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Expressions.Ada_VariableExpression;
import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Ada_AppendFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Ada_Keyword APPEND = new Ada_Keyword("Append");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Ada_VariableExpression varExpr;
	public @S(40) PunctuationComma comma;
	public @S(50) Ada_Expression expr;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ada_Identifier_Reference id = varExpr.variable.vars.first();
		EagleValue var = interpreter.findSymbol(id.toString());
		String val = interpreter.getStrValue(expr);
		EagleString v = new EagleString(var.forceStringValue() + val);
		interpreter.setSymbol(varExpr, id.getValue(), v);
	}
}
