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
import com.eagle.transform.EagleGenerator.BuiltInEnum;

public class Python_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Python_KeywordChoice builtins = new Python_KeywordChoice("None", "False", "True");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtins.toString())
		{
		case "False":
			interpreter.pushBool(false);
			break;
		case "True":
			interpreter.pushBool(true);
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtins);
		}
	}

	public Python_Expression generateBuiltIn(BuiltInEnum builtin, AbstractToken source)
	{
		Python_BuiltIn expr = new Python_BuiltIn();
		switch (builtin)
		{
		case TRUE:
			expr.builtins = new Python_KeywordChoice("True");
			break;
		case FALSE:
			expr.builtins = new Python_KeywordChoice("False");
			break;
		default:
			throw new RuntimeException("Unable to handle: " + builtin.toString());
		}
		return Python_Generator.wrapExpression(expr);
	}
}
