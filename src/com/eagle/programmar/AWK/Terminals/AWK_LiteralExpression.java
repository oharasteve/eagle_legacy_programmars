// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2026

package com.eagle.programmar.AWK.Terminals;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.AWK_ArgumentList.AWK_MoreArguments;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Expressions.AWK_String;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralExpression;
import com.eagle.transform.EagleTransformer;

public class AWK_LiteralExpression extends TerminalLiteralExpression
{
	public static String interpret(EagleInterpreter interpreter, AWK_ArgumentList argList,
			ArgumentsMetrics metrics)
	{
		String fmt = interpreter.getStrValue(argList.expr);
		ArrayList<PrecedenceChooser> args = new ArrayList<PrecedenceChooser>();
		for (AWK_MoreArguments more : argList.more._elements)
		{
			args.add(more.expr);
		}
		ArrayList<LiteralPiece> pieces = parsePercent(fmt, '%', "ds", args);
		return evaluateLiteral(interpreter, metrics,
				AWK_Expression.class, pieces);
	}
	
	public static AbstractExpression transform(EagleTransformer transformer,
		EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
		AWK_ArgumentList argList, ArrayList<TypeEnum> metrics, AbstractToken source)
	{
		AbstractToken which = argList.expr.getWhich(); 
		if (!(which instanceof AWK_String))
		{
			throw new RuntimeException("Format must be a literal, not " + which);
		}
		AWK_String str = (AWK_String) which;
		String fmt = str.literal.removeQuotes();

		ArrayList<PrecedenceChooser> args = new ArrayList<PrecedenceChooser>();
		for (AWK_MoreArguments more : argList.more._elements)
		{
			args.add(more.expr);
		}
		ArrayList<LiteralPiece> pieces = TerminalLiteralExpression.parsePercent(fmt, '%', "ds", args);
		AbstractExpression result = TerminalLiteralExpression.compileLiteral(transformer, generator, metrics,
				AWK_Expression.class, pieces, source);
		return result;
	}
}
