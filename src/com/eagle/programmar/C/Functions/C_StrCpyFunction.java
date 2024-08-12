// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Expressions.C_VariableExpression;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_StrCpyFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) C_Keyword STRCPY = new C_Keyword("strcpy");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_VariableExpression varExpr;
	public @S(40) PunctuationComma comma;
	public @S(50) C_Expression expr;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		C_Variable var = varExpr.variable;
		C_Identifier_Reference id = (C_Identifier_Reference) var.firstId.getWhich();
		String varName = id.getValue();
		String str = interpreter.getStrValue(expr);
		EagleString val = new EagleString(str);
		interpreter.setSymbol(var, varName, val);
	}
}
