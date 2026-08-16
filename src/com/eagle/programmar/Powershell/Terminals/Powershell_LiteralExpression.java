// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 16, 2026

package com.eagle.programmar.Powershell.Terminals;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralExpression;
import com.eagle.transform.EagleTransformer;

public class Powershell_LiteralExpression extends TerminalLiteralExpression
{
	public static String interpret(EagleInterpreter interpreter, String txt, ArgumentsMetrics metrics)
	{
		String fmt = txt;
		if (fmt.startsWith("'"))
		{
			return fmt.substring(1, fmt.length() - 2);
		}
		if (fmt.startsWith("\""))
		{
			fmt = fmt.substring(1, fmt.length() - 2);
		}
		if (fmt.indexOf('$') < 0)
		{
			return fmt;
		}

		ArrayList<LiteralPiece> pieces = parseBashDollar(fmt, '`', '$', '{', '}');
		return evaluateLiteral(interpreter, metrics, Powershell_Expression.class, pieces);
	}
	
	public static AbstractExpression transform(EagleTransformer transformer,
		EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
		Powershell_Literal lit, ArrayList<TypeEnum> metrics, AbstractToken source)
	{
		String fmt = lit.getValue();
		int nc = fmt.length();
		if (fmt.startsWith("'") && fmt.endsWith("'") && nc >= 2)
		{
			return generator.newLiteralExpression(fmt.substring(1, nc - 1), source);
		}
		if (fmt.startsWith("\"") && fmt.endsWith("\"") && nc >= 2)
		{
			fmt = fmt.substring(1, nc - 1);
		}
		if (fmt.indexOf('$') < 0)
		{
			return generator.newLiteralExpression(fmt, source);
		}

		ArrayList<LiteralPiece> pieces = parseBashDollar(fmt, '`', '$', '{', '}');
		AbstractExpression result = TerminalLiteralExpression.compileLiteral(transformer, generator, metrics,
				Powershell_Expression.class, pieces, source);
		return result;
	}
}
