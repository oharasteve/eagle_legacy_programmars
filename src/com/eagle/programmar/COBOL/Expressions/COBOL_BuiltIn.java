// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BuiltInEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class COBOL_BuiltIn extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) COBOL_KeywordChoice logicalConstant = new COBOL_KeywordChoice("ANY", "FALSE", "HIGH-VALUES",
			"LINAGE-COUNTER", "LOW-VALUES", "QUOTE", "RETURN-CODE", "SPACE", "SPACES", "TRUE", "ZERO", "ZEROES",
			"ZEROS");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = logicalConstant.toString().toUpperCase();
		switch (name)
		{
		case "FALSE":
			interpreter.pushBool(false);
			break;
		case "TRUE":
			interpreter.pushBool(true);
			break;
		case "SPACES":
			interpreter.pushStr("");
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than TRUE/FALSE: " + name);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		switch (logicalConstant.toString().toUpperCase())
		{
		case "FALSE":
			return generator.newBuiltInExpression(BuiltInEnum.FALSE, this);
		case "TRUE":
			return generator.newBuiltInExpression(BuiltInEnum.TRUE, this);
		case "SPACES":
			return generator.newLiteralExpression("", this);
		default:
			throw new RuntimeException("Can't handle BuiltIn: " + logicalConstant);
		}
	}
}
