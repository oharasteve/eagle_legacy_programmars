// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Terminals;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralExpression;
import com.eagle.tokens.terminals.TerminalLiteralExpression.LiteralPiece;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Eaglish_Literal extends TerminalLiteralToken
		implements EagleTransformableExpression
{
	public Eaglish_Literal()
	{
		super("\"", true, '\\', false, false);
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		ArgumentsMetrics metrics = null;
		ArrayList<LiteralPiece> pieces = TerminalLiteralExpression.parsePair(removeQuotes(), '\\', '^', '^');
		String result = TerminalLiteralExpression.evaluateLiteral(interpreter, metrics, Eaglish_Expression.class, pieces);
		interpreter.pushStr(result);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<TypeEnum> metrics = null;
		ArrayList<LiteralPiece> pieces = TerminalLiteralExpression.parsePair(removeQuotes(), '\\', '^', '^');
		AbstractExpression result = TerminalLiteralExpression.compileLiteral(transformer, generator, metrics,
				Eaglish_Expression.class, pieces, this);
		return result;
	}
}
