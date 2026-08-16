// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Terminals;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralExpression.LiteralPiece;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class TCL_Literal extends TerminalLiteralToken
		implements EagleRunnable, EagleTransformableExpression
{
	public TCL_Literal()
	{
		super("\"", true, '\\', false, false);
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String value = removeQuotes();
		if (value.indexOf('[') >= 0 || value.indexOf('$') >= 0)
		{
			ArgumentsMetrics metrics = null;
			ArrayList<LiteralPiece> pieces = TCL_LiteralExpression.parseBracketDollar(value);
			value = TCL_LiteralExpression.evaluateLiteral(interpreter, metrics, TCL_Expression.class, pieces);
		}
		interpreter.pushStr(value);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String value = removeQuotes();
		if (value.indexOf('[') < 0 && value.indexOf('$') < 0)
		{
			return generator.newLiteralExpression(value, this);
		}
		return TCL_LiteralExpression.transform(transformer, generator, value, this);
	}
}
