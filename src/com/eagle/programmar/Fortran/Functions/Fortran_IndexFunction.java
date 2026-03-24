// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2026

package com.eagle.programmar.Fortran.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Fortran_Variable;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Fortran_IndexFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Fortran_Keyword INDEX = new Fortran_Keyword("INDEX");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Fortran_Variable variable;
	public @S(40) PunctuationComma comma;
	public @S(50) Fortran_Expression pattern;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(variable);
		String patt = interpreter.getStrValue(pattern);
		int pos = str.indexOf(patt);
		if (pos < 0)
		{
			interpreter.pushInt(0);
		}
		else
		{
			interpreter.pushInt(pos + 1);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractVariable var = generator.newVariable(variable.var.getValue());
		AbstractExpression pattExpr = transformer.transformExpression(generator, pattern);
		AbstractExpression indexFunc = generator.newIndexOfFunction(var, pattExpr, null,
				SubstringSCEnum.FIRST_CHAR_IS_ONE, this);
		AbstractExpression oneExpr = generator.newNumberExpression("1", null);
		Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
		return generator.newAdditiveExpression(types, indexFunc, AdditiveEnum.PLUS, oneExpr, this);
	}
}