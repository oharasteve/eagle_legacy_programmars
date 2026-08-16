// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2026

package com.eagle.programmar.Bash.Terminals;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralExpression;
import com.eagle.transform.EagleTransformer;

public class Bash_LiteralExpression extends TerminalLiteralExpression
{
	public static String interpret(EagleInterpreter interpreter, String fmt,
			ArgumentsMetrics metrics)
	{
		if (fmt.startsWith("'"))
		{
			return fmt.substring(1, fmt.length() - 2);
		}
		if (fmt.indexOf('$') < 0)
		{
			return fmt;
		}

		ArrayList<LiteralPiece> pieces = parseBashDollar(fmt, '\\', '$', '{', '}');
		return evaluateLiteral(interpreter, metrics, Bash_Expression.class, pieces);
	}
	
	public static AbstractExpression UNUSED_UNTESTED_transform(EagleTransformer transformer,
		EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
		Bash_Literal lit, ArrayList<TypeEnum> metrics, AbstractToken source)
	{
		String fmt = lit.getValue();
		if (fmt.startsWith("'"))
		{
			return generator.newLiteralExpression(fmt.substring(1, fmt.length() - 1), source);
		}
		if (fmt.indexOf('$') < 0)
		{
			return generator.newLiteralExpression(fmt, source);
		}
		if (fmt.startsWith("\""))
		{
			fmt = fmt.substring(1, fmt.length() - 1);
		}

		ArrayList<LiteralPiece> pieces = parseBashDollar(fmt, '\\', '$', '{', '}');
		AbstractExpression result = TerminalLiteralExpression.compileLiteral(transformer, generator, metrics,
				Bash_Expression.class, pieces, source);
		return result;
	}
}
