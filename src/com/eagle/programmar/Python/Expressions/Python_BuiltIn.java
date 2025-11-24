// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BuiltInEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_BuiltIn extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_KeywordChoice builtIn = new Python_KeywordChoice("None", "False", "True");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtIn.toString())
		{
		case "False":
			interpreter.pushBool(false);
			break;
		case "True":
			interpreter.pushBool(true);
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtIn);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		switch (builtIn.toString())
		{
		case "False":
			return generator.newBuiltInExpression(BuiltInEnum.FALSE, this);
		case "True":
			return generator.newBuiltInExpression(BuiltInEnum.TRUE, this);
		default:
			throw new RuntimeException("Can't handle BuiltIn: " + builtIn);
		}
	}

	public static Python_Expression generateBuiltIn(BuiltInEnum builtin, AbstractToken source)
	{
		Python_BuiltIn expr = new Python_BuiltIn();
		switch (builtin)
		{
		case TRUE:
			expr.builtIn = new Python_KeywordChoice("True");
			break;
		case FALSE:
			expr.builtIn = new Python_KeywordChoice("False");
			break;
		case NULL:
			expr.builtIn = new Python_KeywordChoice("None");
			break;
		default:
			throw new RuntimeException("Unable to handle: " + builtin.toString());
		}
		return Python_Generator.wrapExpression(expr);
	}
}
