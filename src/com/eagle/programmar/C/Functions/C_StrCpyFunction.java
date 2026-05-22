// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Expressions.C_VariableExpression;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class C_StrCpyFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
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

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractToken which = varExpr.variable.firstId.getWhich();
		if (!(which instanceof C_Identifier_Reference))
		{
			throw new RuntimeException("Must be a regular variable");
		}
		C_Identifier_Reference id = (C_Identifier_Reference) which;
		AbstractExpression newValue = transformer.transformExpression(generator, expr);
		return generator.newAssignmentExpression(id.getValue(), SubscriptEnum.FIRST_IS_ZERO, null,
				AssignmentEnum.EQUALS, newValue, STRCPY);
	}
}
