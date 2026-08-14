// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2026

package com.eagle.programmar.Ruby.Terminals;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralExpression;
import com.eagle.transform.EagleTransformer;

public class Ruby_LiteralExpression extends TerminalLiteralExpression
{
	public static String interpret(EagleInterpreter interpreter, Ruby_Expression expr, ArgumentsMetrics metrics)
	{
		AbstractToken which = expr.getWhich();
		if (!(which instanceof Ruby_Literal))
		{
			throw new RuntimeException("Format must be a literal, not " + which);
		}
		Ruby_Literal str = (Ruby_Literal) which;
		String fmt = str.removeQuotes();

		ArrayList<LiteralPiece> pieces = parsePair2(fmt, '\\', '#', '{', '}');
		return evaluateLiteral(interpreter, metrics, Ruby_Expression.class, pieces);
	}
	
	public static AbstractExpression transform(EagleTransformer transformer,
		EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
		Ruby_Expression expr, ArrayList<TypeEnum> metrics, AbstractToken source)
	{
		AbstractToken which = expr.getWhich();
		if (!(which instanceof Ruby_Literal))
		{
			throw new RuntimeException("Format must be a literal, not " + which);
		}
		Ruby_Literal str = (Ruby_Literal) which;
		String fmt = str.removeQuotes();

		ArrayList<LiteralPiece> pieces = parsePair2(fmt, '\\', '#', '{', '}');
		AbstractExpression result = TerminalLiteralExpression.compileLiteral(transformer, generator, metrics,
				Ruby_Expression.class, pieces, source);
		return result;
	}
}
