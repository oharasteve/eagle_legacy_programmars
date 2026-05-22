// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class C_StrCmpFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) C_KeywordChoice STRCMP = new C_KeywordChoice("strcmp", "stricmp", "strcasecmp");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Expression str1;
	public @S(40) PunctuationComma comma;
	public @S(50) C_Expression str2;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) C_PunctuationChoice operator = new C_PunctuationChoice("==", "!=", "<", ">=");
	public @S(80) C_Keyword ZERO = new C_Keyword("0");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String left = interpreter.getStrValue(str1);
		String right = interpreter.getStrValue(str2);
		int compare = -1;
		switch (STRCMP.getValue())
		{
		case "strcmp":
		default:
			compare = left.compareTo(right);
			break;
		case "strcasecmp":
		case "stricmp":
			compare = left.compareToIgnoreCase(right);
			break;
		}

		switch (operator.getValue())
		{
		case "==", ">=":
			interpreter.pushBool(compare == 0);
			return;
		case "!=", "<":
			interpreter.pushBool(compare != 0);
			return;
		}

		throw new RuntimeException("Unexpected operator: " + operator.getValue());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		Oper2Types types = new Oper2Types(TypeEnum.STRING, TypeEnum.STRING);
		AbstractExpression newStr1 = transformer.transformExpression(generator, str1);
		AbstractExpression newStr2 = transformer.transformExpression(generator, str2);

		switch (operator.getValue())
		{
		case "==", ">=":
			return generator.newRelationalExpression(types, newStr1, RelationalEnum.EQUALS, newStr2, this);
		case "!=", "<":
			return generator.newRelationalExpression(types, newStr1, RelationalEnum.NOT_EQUALS, newStr2, this);
		}

		throw new RuntimeException("Unexpected operator: " + operator.getValue());
	}
}
